import java.util.ArrayList;

public class Slide07ForeachAssignmentBehavior {
    public static void main(String[] args) {
        ArrayList<String> userNames = new ArrayList<>();

        userNames.add("Alice");
        userNames.add("Bobby");
        userNames.add("Charlie");

        for (String userName : userNames) {
            userName = "Unknown";
        }

        System.out.println(userNames);
    }
}
