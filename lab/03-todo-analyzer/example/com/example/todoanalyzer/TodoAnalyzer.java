package com.example.todoanalyzer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TodoAnalyzer {
    private static final String LINE_TODO_PATTERN = "// TODO: ";
    private static final String BLOCK_TODO_PATTERN = "/* TODO: ";
    private static final String BLOCK_TODO_END = "*/";

    private static final List<String> ALLOWED_EXTENSIONS = List.of(
            ".c", ".cpp", ".h", ".java", ".cs", ".py", ".js", ".ts", ".html"
    );

    private TodoAnalyzer() {
    }

    public static String generateTodoReportOrNull(String directoryPathOrNull) {
        if (!isValidDirectoryPath(directoryPathOrNull)) {
            return null;
        }

        Path directoryPath = Paths.get(directoryPathOrNull);

        List<Path> filesInDirectory = listRegularFilesOrNull(directoryPath);
        if (filesInDirectory == null) {
            return null;
        }

        filesInDirectory.sort(Comparator.comparing(path -> path.getFileName().toString()));

        Map<String, List<String>> todosByFileName = new HashMap<>();

        for (Path filePath : filesInDirectory) {
            String fileName = filePath.getFileName().toString();
            if (!hasAllowedExtension(fileName)) {
                continue;
            }

            String fileContent = readStringOrNull(filePath);
            if (fileContent == null) {
                return null;
            }

            List<String> extractedTodos = extractTodos(fileContent);
            if (!extractedTodos.isEmpty()) {
                todosByFileName.put(fileName, extractedTodos);
            }
        }

        String reportContent = buildReportContent(filesInDirectory, todosByFileName);

        Path reportPath = createNextReportPath(directoryPath);
        if (!writeString(reportPath, reportContent)) {
            return null;
        }

        return reportPath.toAbsolutePath().toString();
    }

    private static boolean isValidDirectoryPath(String directoryPathOrNull) {
        if (directoryPathOrNull == null) {
            return false;
        }

        if (directoryPathOrNull.length() < 1) {
            return false;
        }

        if (isBlank(directoryPathOrNull)) {
            return false;
        }

        Path directoryPath = toPathOrNull(directoryPathOrNull);
        if (directoryPath == null) {
            return false;
        }

        return Files.isDirectory(directoryPath);
    }

    private static Path toPathOrNull(String pathOrNull) {
        try {
            return Paths.get(pathOrNull);
        } catch (InvalidPathException ignored) {
            return null;
        }
    }

    private static List<Path> listRegularFilesOrNull(Path directory) {
        try {
            return listRegularFiles(directory);
        } catch (IOException ignored) {
            return null;
        }
    }

    private static String readStringOrNull(Path filePath) {
        try {
            return Files.readString(filePath, StandardCharsets.UTF_8);
        } catch (IOException ignored) {
            return null;
        }
    }

    private static boolean writeString(Path filePath, String content) {
        try {
            Files.writeString(filePath, content, StandardCharsets.UTF_8);
        } catch (IOException ignored) {
            return false;
        }

        return true;
    }

    private static boolean isBlank(String text) {
        for (int i = 0; i < text.length(); ++i) {
            if (!Character.isWhitespace(text.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    private static List<Path> listRegularFiles(Path directory) throws IOException {
        List<Path> regularFiles = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path entryPath : stream) {
                if (Files.isRegularFile(entryPath)) {
                    regularFiles.add(entryPath);
                }
            }
        }

        return regularFiles;
    }

    private static boolean hasAllowedExtension(String fileName) {
        String lowerFileName = fileName.toLowerCase(Locale.ROOT);

        for (String extension : ALLOWED_EXTENSIONS) {
            if (lowerFileName.endsWith(extension)) {
                return true;
            }
        }

        return false;
    }

    private static List<String> extractTodos(String content) {
        List<String> todos = new ArrayList<>();

        int contentLength = content.length();
        int linePatternLength = LINE_TODO_PATTERN.length();
        int blockPatternLength = BLOCK_TODO_PATTERN.length();
        int blockEndLength = BLOCK_TODO_END.length();

        int index = 0;
        while (index < contentLength) {
            if (startsWithAt(content, LINE_TODO_PATTERN, index)) {
                int todoStartIndex = index + linePatternLength;
                int todoEndIndex = findLineEnd(content, todoStartIndex);
                String rawTodo = content.substring(todoStartIndex, todoEndIndex);

                addNormalizedTodoIfNotEmpty(todos, rawTodo);

                index = todoEndIndex;
                continue;
            }

            if (startsWithAt(content, BLOCK_TODO_PATTERN, index)) {
                int todoStartIndex = index + blockPatternLength;
                int todoEndIndex = content.indexOf(BLOCK_TODO_END, todoStartIndex);

                if (todoEndIndex < 0) {
                    break;
                }

                String rawTodo = content.substring(todoStartIndex, todoEndIndex);
                addNormalizedTodoIfNotEmpty(todos, rawTodo);

                index = todoEndIndex + blockEndLength;
                continue;
            }

            ++index;
        }

        return todos;
    }

    private static boolean startsWithAt(String content, String pattern, int index) {
        if (index + pattern.length() > content.length()) {
            return false;
        }

        for (int offset = 0; offset < pattern.length(); ++offset) {
            if (content.charAt(index + offset) != pattern.charAt(offset)) {
                return false;
            }
        }

        return true;
    }

    private static int findLineEnd(String content, int startIndex) {
        int index = startIndex;
        while (index < content.length()) {
            char character = content.charAt(index);
            if (character == '\n' || character == '\r') {
                break;
            }

            ++index;
        }

        return index;
    }

    private static void addNormalizedTodoIfNotEmpty(List<String> todos, String rawTodo) {
        String normalizedTodo = normalizeTodo(rawTodo);
        if (normalizedTodo.length() == 0) {
            return;
        }

        todos.add(normalizedTodo);
    }

    private static String normalizeTodo(String rawTodo) {
        String trimmed = trimAllWhitespace(rawTodo);
        if (trimmed.length() == 0) {
            return "";
        }

        StringBuilder normalizedBuilder = new StringBuilder(trimmed.length());

        boolean previousWasWhitespace = false;
        for (int i = 0; i < trimmed.length(); ++i) {
            char character = trimmed.charAt(i);

            if (Character.isWhitespace(character)) {
                if (!previousWasWhitespace) {
                    normalizedBuilder.append(' ');
                    previousWasWhitespace = true;
                }

                continue;
            }

            normalizedBuilder.append(character);
            previousWasWhitespace = false;
        }

        if (normalizedBuilder.length() > 0 && normalizedBuilder.charAt(normalizedBuilder.length() - 1) == ' ') {
            normalizedBuilder.setLength(normalizedBuilder.length() - 1);
        }

        return normalizedBuilder.toString();
    }

    private static String trimAllWhitespace(String text) {
        int leftIndex = 0;
        while (leftIndex < text.length() && Character.isWhitespace(text.charAt(leftIndex))) {
            ++leftIndex;
        }

        int rightIndex = text.length() - 1;
        while (rightIndex >= leftIndex && Character.isWhitespace(text.charAt(rightIndex))) {
            --rightIndex;
        }

        return text.substring(leftIndex, rightIndex + 1);
    }

    private static String buildReportContent(List<Path> sortedFiles, Map<String, List<String>> todosByFileName) {
        StringBuilder reportBuilder = new StringBuilder();

        boolean isFirstFileBlock = true;

        for (Path filePath : sortedFiles) {
            String fileName = filePath.getFileName().toString();
            List<String> todos = todosByFileName.get(fileName);

            if (todos == null || todos.isEmpty()) {
                continue;
            }

            if (!isFirstFileBlock) {
                reportBuilder.append('\n');
                reportBuilder.append('\n');
            }

            reportBuilder.append(fileName);
            reportBuilder.append('\n');

            for (int i = 0; i < todos.size(); ++i) {
                reportBuilder.append("- ");
                reportBuilder.append(todos.get(i));

                if (i != todos.size() - 1) {
                    reportBuilder.append('\n');
                }
            }

            isFirstFileBlock = false;
        }

        return reportBuilder.toString();
    }

    private static Path createNextReportPath(Path directory) {
        Path reportPath = directory.resolve("report.txt");
        if (!Files.exists(reportPath)) {
            return reportPath;
        }

        int suffixNumber = 2;
        while (true) {
            reportPath = directory.resolve("report-" + suffixNumber + ".txt");
            if (!Files.exists(reportPath)) {
                return reportPath;
            }

            ++suffixNumber;
        }
    }
}
