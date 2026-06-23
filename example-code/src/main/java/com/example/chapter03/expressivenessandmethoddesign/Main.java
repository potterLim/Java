package com.example.chapter03.expressivenessandmethoddesign;

public class Main {
    public static void main(String[] args) {
        System.out.println("3장. 표현력과 메서드 설계");
        System.out.println("==================================================");

        demonstrateMethodOverloading();
        demonstrateMethodSignature();
        demonstrateDangerousOverloading();
        demonstrateStringConcatenation();
        demonstrateStringBuilderBasics();
        demonstrateStringBuilderCapacityGrowth();
        demonstrateAdditionalStringBuilderMethods();
    }

    private static void demonstrateMethodOverloading() {
        printSectionTitle("메서드 오버로딩");

        notifyUser("서버가 시작되었습니다.");
        notifyUser("AUTH", "로그인에 성공했습니다.");
        notifyUser("PAYMENT", "결제가 승인되었습니다.", 3);
    }

    private static void notifyUser(String message) {
        System.out.println("[INFO] " + message);
    }

    private static void notifyUser(String title, String message) {
        System.out.println("[" + title + "] " + message);
    }

    private static void notifyUser(String title, String message, int priority) {
        System.out.println("[P" + priority + "] [" + title + "] " + message);
    }

    private static void demonstrateMethodSignature() {
        printSectionTitle("메서드 시그니처");

        int[] scores = { 90, 80, 100 };
        int averageScore = calculateAverageScore(scores);

        System.out.println("averageScore = " + averageScore);

        // 반환형만 다른 메서드는 오버로딩할 수 없다.
        // public static double calculateAverageScore(int[] scores);
    }

    private static int calculateAverageScore(int[] scores) {
        assert (scores != null) : "scores는 null이면 안 됩니다.";
        assert (scores.length > 0) : "scores에는 점수가 하나 이상 있어야 합니다.";

        int totalScore = 0;

        for (int i = 0; i < scores.length; ++i) {
            totalScore += scores[i];
        }

        return totalScore / scores.length;
    }

    private static void demonstrateDangerousOverloading() {
        printSectionTitle("위험해질 수 있는 오버로딩");

        int price = 100_000;
        int discountPercent = 15;

        int actualFinalPrice = applyDiscount(discountPercent, price);
        int expectedFinalPrice = applyDiscount((double) discountPercent, price);

        System.out.println("actualFinalPrice = " + actualFinalPrice);
        System.out.println("expectedFinalPrice = " + expectedFinalPrice);
    }

    private static int applyDiscount(double discountPercent, int price) {
        return (int) (price - (price * discountPercent / 100));
    }

    private static int applyDiscount(int discountAmount, int price) {
        return price - discountAmount;
    }

    private static void demonstrateStringConcatenation() {
        printSectionTitle("반복되는 String 연결");

        String[] errorMessages = { "파일을 찾을 수 없음", "잘못된 입력", "네트워크 시간 초과" };
        String logMessage = "";

        for (int i = 0; i < errorMessages.length; ++i) {
            logMessage = logMessage + "[ERROR] " + errorMessages[i] + "\n";
        }

        System.out.print(logMessage);
    }

    private static void demonstrateStringBuilderBasics() {
        printSectionTitle("StringBuilder 기본 사용");

        StringBuilder messageBuilder = new StringBuilder(128);

        messageBuilder
                .append("Why not ")
                .append("change ")
                .append("the world?");

        String message = messageBuilder.toString();

        System.out.println(message);
    }

    private static void demonstrateStringBuilderCapacityGrowth() {
        printSectionTitle("StringBuilder의 length와 capacity");

        StringBuilder messageBuilder = new StringBuilder(8);

        messageBuilder.append("Why not ");
        printLengthAndCapacity(messageBuilder);

        messageBuilder.append("change ");
        printLengthAndCapacity(messageBuilder);
    }

    private static void printLengthAndCapacity(StringBuilder messageBuilder) {
        assert (messageBuilder != null) : "messageBuilder는 null이면 안 됩니다.";

        int length = messageBuilder.length();
        int capacity = messageBuilder.capacity();

        System.out.println("length = " + length + ", capacity = " + capacity);
    }

    private static void demonstrateAdditionalStringBuilderMethods() {
        printSectionTitle("추가로 알아두면 좋은 StringBuilder 메서드");

        StringBuilder commandBuilder = new StringBuilder("deploy service");

        commandBuilder.insert(0, "please ");
        commandBuilder.replace(7, 13, "restart");
        commandBuilder.delete(commandBuilder.length() - " service".length(), commandBuilder.length());
        commandBuilder.append("!");
        commandBuilder.deleteCharAt(commandBuilder.length() - 1);

        char firstCharacter = commandBuilder.charAt(0);
        String command = commandBuilder.toString();

        System.out.println("첫 번째 문자 = " + firstCharacter);
        System.out.println("command = " + command);
    }

    private static void printSectionTitle(String title) {
        System.out.println();
        System.out.println("[" + title + "]");
        System.out.println("--------------------------------------------------");
    }
}
