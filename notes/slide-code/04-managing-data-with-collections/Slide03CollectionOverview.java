import java.util.ArrayList;

public class Slide03CollectionOverview {
    public static void main(String[] args) {
        ArrayList<String> todoItems = new ArrayList<>();

        todoItems.add("Buy milk");
        todoItems.add("Submit assignment");
        todoItems.add("Call mom");

        for (int i = 0; i < todoItems.size(); ++i) {
            System.out.println("- " + todoItems.get(i));
        }
    }
}
