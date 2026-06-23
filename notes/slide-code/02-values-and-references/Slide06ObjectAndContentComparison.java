import java.util.Arrays;

public class Slide06ObjectAndContentComparison {
    public static void main(String[] args) {
        int[] firstScores = { 90, 80, 100 };
        int[] secondScores = { 90, 80, 100 };

        boolean isSameObject = firstScores == secondScores;
        boolean isSameContent = Arrays.equals(firstScores, secondScores);

        System.out.println("isSameObject = " + isSameObject);
        System.out.println("isSameContent = " + isSameContent);
    }
}
