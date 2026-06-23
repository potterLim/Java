import java.util.HashMap;

public class Slide17HashMapOperations {
    public static void main(String[] args) {
        HashMap<Integer, String> studentNamesById = new HashMap<>();

        studentNamesById.put(1001, "Alice");
        studentNamesById.put(1002, "Alice");
        String previousStudentName = studentNamesById.put(1002, "Bobby");

        String selectedStudentName = studentNamesById.get(1002);
        boolean hasStudentId = studentNamesById.containsKey(1003);
        String removedStudentName = studentNamesById.remove(1001);
        int studentCount = studentNamesById.size();

        System.out.println("studentNamesById = " + studentNamesById);
        System.out.println("previousStudentName = " + previousStudentName);
        System.out.println("selectedStudentName = " + selectedStudentName);
        System.out.println("hasStudentId = " + hasStudentId);
        System.out.println("removedStudentName = " + removedStudentName);
        System.out.println("studentCount = " + studentCount);
    }
}
