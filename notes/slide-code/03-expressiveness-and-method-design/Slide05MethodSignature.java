public class Slide05MethodSignature {
    public static void main(String[] args) {
        int[] scores = { 90, 80, 100 };

        int averageScore = calculateAverageScore(scores);

        System.out.println("averageScore = " + averageScore);
    }

    public static int calculateAverageScore(int[] scores) {
        int totalScore = 0;

        for (int i = 0; i < scores.length; ++i) {
            totalScore += scores[i];
        }

        return totalScore / scores.length;
    }

    // 반환형만 다른 메서드는 오버로딩할 수 없다.
    // public static double calculateAverageScore(int[] scores);
}
