import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Slide10VarInForeach {
    public static void main(String[] args) {
        ArrayList<String> userNames = new ArrayList<>();

        userNames.add("Alice");
        userNames.add("Bobby");
        userNames.add("Charlie");

        for (var userName : userNames) {
            System.out.println(userName);
        }

        HashSet<String> processedEmails = new HashSet<>();

        processedEmails.add("alice@example.com");
        processedEmails.add("bobby@example.com");
        processedEmails.add("charlie@example.com");

        for (var email : processedEmails) {
            System.out.println(email);
        }

        HashMap<String, Long> lastLoginTimesByUserName = new HashMap<>();

        lastLoginTimesByUserName.put("alice", 1735689600L);
        lastLoginTimesByUserName.put("bobby", 1740830400L);
        lastLoginTimesByUserName.put("charlie", 1750002600L);

        for (var entry : lastLoginTimesByUserName.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
