import java.util.ArrayList;

public class Slide06ArrayListOperations {
    public static void main(String[] args) {
        ArrayList<Integer> searchResultIds = new ArrayList<>();

        searchResultIds.add(101);
        searchResultIds.add(101);
        searchResultIds.add(309);
        searchResultIds.add(1, 150);

        int selectedResultId = searchResultIds.get(1);
        int replacedResultId = searchResultIds.set(1, 210);
        int removedResultId = searchResultIds.remove(0);
        int searchResultCount = searchResultIds.size();

        System.out.println("searchResultIds = " + searchResultIds);
        System.out.println("selectedResultId = " + selectedResultId);
        System.out.println("replacedResultId = " + replacedResultId);
        System.out.println("removedResultId = " + removedResultId);
        System.out.println("searchResultCount = " + searchResultCount);
    }
}
