import java.util.List;

public class Slide08StringConcatenation {
    public static void main(String[] args) {
        List<String> errors = List.of("File not found", "Invalid input");

        String logMessage = "";
        for (int i = 0; i < errors.size(); ++i) {
            logMessage = logMessage + "[ERROR]" + errors.get(i) + "\n";
        }

        System.out.print(logMessage);
    }
}
