import java.util.HashSet;

public class Slide12HashSetOperations {
    public static void main(String[] args) {
        HashSet<String> processedUsers = new HashSet<>();

        boolean isNewUser;
        isNewUser = processedUsers.add("Alice");
        System.out.println("add Alice = " + isNewUser);

        isNewUser = processedUsers.add("Alice");
        System.out.println("add Alice again = " + isNewUser);

        isNewUser = processedUsers.add("Bobby");
        System.out.println("add Bobby = " + isNewUser);

        boolean isProcessed = processedUsers.contains("Bobby");
        boolean isRemoved = processedUsers.remove("Alice");
        int processedUserCount = processedUsers.size();

        System.out.println("isProcessed = " + isProcessed);
        System.out.println("isRemoved = " + isRemoved);
        System.out.println("processedUserCount = " + processedUserCount);
    }
}
