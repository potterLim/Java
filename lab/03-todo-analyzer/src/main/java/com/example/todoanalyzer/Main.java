package com.example.todoanalyzer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

public class Main {
    private static int sPassedCount;
    private static int sFailedCount;
    private static String sFailureMessage;

    private Main() {
    }

    public static void main(String[] args) {
        verifyInvalidDirectory();
        verifyTodoReportGeneration();
        verifyReportNameConflictAndEmptyReport();

        printSummary();
    }

    private static void verifyInvalidDirectory() {
        Path filePathOrNull = null;

        try {
            filePathOrNull = Files.createTempFile("todo-analyzer-main-", ".txt");

            boolean isPassed = checkNull("디렉터리 경로가 null인 경우", TodoAnalyzer.generateTodoReportOrNull(null))
                    && checkNull("디렉터리 경로가 공백인 경우", TodoAnalyzer.generateTodoReportOrNull("   "))
                    && checkNull("파일 경로가 전달된 경우", TodoAnalyzer.generateTodoReportOrNull(filePathOrNull.toString()));

            verifyScenario("잘못된 디렉터리 입력은 null 반환", isPassed);
        } catch (IOException exception) {
            fail("잘못된 디렉터리 입력 검증 준비", exception.getMessage());
        } finally {
            deletePathIfExists(filePathOrNull);
        }
    }

    private static void verifyTodoReportGeneration() {
        Path directoryPathOrNull = null;

        try {
            directoryPathOrNull = Files.createTempDirectory("todo-analyzer-main-");

            Files.writeString(
                    directoryPathOrNull.resolve("alpha.c"),
                    String.join(
                            "\n",
                            "int add(int a, int b)",
                            "{",
                            "    return a + b; // TODO: handle overflow later",
                            "}"
                    ),
                    StandardCharsets.UTF_8
            );

            Files.writeString(
                    directoryPathOrNull.resolve("beta.java"),
                    String.join(
                            "\n",
                            "public class Beta {",
                            "    public static void main(String[] args) {",
                            "    /* TODO: refactor main logic",
                            "             - extract method",
                            "             - remove duplication ",
                            "    */",
                            "    // TODO: add input validation",
                            "    }",
                            "}"
                    ),
                    StandardCharsets.UTF_8
            );

            Files.writeString(
                    directoryPathOrNull.resolve("ignored.md"),
                    "// TODO: this file extension is ignored",
                    StandardCharsets.UTF_8
            );

            Path nestedDirectoryPath = Files.createDirectory(directoryPathOrNull.resolve("nested"));
            Files.writeString(
                    nestedDirectoryPath.resolve("nested.java"),
                    "// TODO: nested directories are ignored",
                    StandardCharsets.UTF_8
            );

            String reportPathOrNull = TodoAnalyzer.generateTodoReportOrNull(directoryPathOrNull.toString());
            boolean isPassed = checkNotNull("리포트 파일 경로", reportPathOrNull);

            if (isPassed) {
                Path reportPath = Path.of(reportPathOrNull);
                String expectedReport = String.join(
                        "\n",
                        "alpha.c",
                        "- handle overflow later",
                        "",
                        "beta.java",
                        "- refactor main logic - extract method - remove duplication",
                        "- add input validation"
                );

                String actualReport = Files.readString(reportPath, StandardCharsets.UTF_8);
                isPassed = checkEquals("리포트 파일 이름", "report.txt", reportPath.getFileName().toString())
                        && checkEquals("리포트 내용", expectedReport, actualReport);
            }

            verifyScenario("TODO 리포트 생성과 파일명 정렬", isPassed);
        } catch (IOException exception) {
            fail("TODO 리포트 생성 검증 준비", exception.getMessage());
        } finally {
            deleteDirectoryTree(directoryPathOrNull);
        }
    }

    private static void verifyReportNameConflictAndEmptyReport() {
        Path directoryPathOrNull = null;

        try {
            directoryPathOrNull = Files.createTempDirectory("todo-analyzer-main-empty-");
            Files.writeString(directoryPathOrNull.resolve("report.txt"), "existing report", StandardCharsets.UTF_8);
            Files.writeString(
                    directoryPathOrNull.resolve("notes.java"),
                    "public class Notes {}",
                    StandardCharsets.UTF_8
            );

            String reportPathOrNull = TodoAnalyzer.generateTodoReportOrNull(directoryPathOrNull.toString());
            boolean isPassed = checkNotNull("리포트 파일 경로", reportPathOrNull);

            if (isPassed) {
                Path reportPath = Path.of(reportPathOrNull);
                String actualReport = Files.readString(reportPath, StandardCharsets.UTF_8);
                isPassed = checkEquals("다음 리포트 파일 이름", "report-2.txt", reportPath.getFileName().toString())
                        && checkEquals("TODO가 없는 리포트 내용", "", actualReport);
            }

            verifyScenario("리포트 이름 충돌과 빈 리포트 처리", isPassed);
        } catch (IOException exception) {
            fail("빈 리포트 검증 준비", exception.getMessage());
        } finally {
            deleteDirectoryTree(directoryPathOrNull);
        }
    }

    private static boolean checkEquals(String name, String expected, String actual) {
        if (expected.equals(actual)) {
            return true;
        }

        sFailureMessage = name + ": 기대=" + expected + ", 실제=" + actual;
        return false;
    }

    private static boolean checkNull(String name, String actual) {
        if (actual == null) {
            return true;
        }

        sFailureMessage = name + ": 기대=null, 실제=" + actual;
        return false;
    }

    private static boolean checkNotNull(String name, String actual) {
        if (actual != null) {
            return true;
        }

        sFailureMessage = name + ": null이 아닌 값을 기대함";
        return false;
    }

    private static void verifyScenario(String name, boolean isPassed) {
        if (isPassed) {
            pass(name);
            return;
        }

        fail(name, sFailureMessage);
        sFailureMessage = null;
    }

    private static void deleteDirectoryTree(Path directoryPathOrNull) {
        if (directoryPathOrNull == null) {
            return;
        }

        try (Stream<Path> paths = Files.walk(directoryPathOrNull)) {
            paths.sorted(Comparator.reverseOrder()).forEach(Main::deletePathIfExists);
        } catch (IOException ignored) {
        }
    }

    private static void deletePathIfExists(Path pathOrNull) {
        if (pathOrNull == null) {
            return;
        }

        try {
            Files.deleteIfExists(pathOrNull);
        } catch (IOException ignored) {
        }
    }

    private static void pass(String name) {
        ++sPassedCount;
        System.out.println("[통과] " + name);
    }

    private static void fail(String name, String message) {
        ++sFailedCount;
        System.out.println("[실패] " + name + " (" + message + ")");
    }

    private static void printSummary() {
        System.out.println();
        System.out.println("통과: " + sPassedCount);
        System.out.println("실패: " + sFailedCount);
    }
}
