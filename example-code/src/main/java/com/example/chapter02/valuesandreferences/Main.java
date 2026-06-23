package com.example.chapter02.valuesandreferences;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("2장. 값과 참조");
        System.out.println("==================================================");

        demonstratePrimitiveValueCopy();
        demonstrateReferenceSharing();
        demonstrateNullReference();
        demonstrateObjectAndContentComparison();
        demonstratePrimitiveParameterPassing();
        demonstrateReferenceParameterPassing();
    }

    private static void demonstratePrimitiveValueCopy() {
        printSectionTitle("기본 자료형 값 복사");

        int originalNumber = 5;
        int copiedNumber = originalNumber;

        copiedNumber = 3;

        System.out.println("originalNumber = " + originalNumber);
        System.out.println("copiedNumber = " + copiedNumber);
    }

    private static void demonstrateReferenceSharing() {
        printSectionTitle("참조값 복사와 같은 대상 공유");

        String[][] memberNames = new String[3][];
        memberNames[0] = new String[11];

        String[] firstTeamMembers = memberNames[0];

        firstTeamMembers[0] = "Alice";
        memberNames[0][1] = "Bob";

        System.out.println("memberNames[0] = " + Arrays.toString(memberNames[0]));
        System.out.println("firstTeamMembers = " + Arrays.toString(firstTeamMembers));
    }

    private static void demonstrateNullReference() {
        printSectionTitle("아무 객체도 가리키지 않는 참조");

        User currentUserOrNull = null;
        boolean hasCurrentUser = currentUserOrNull != null;

        System.out.println("currentUserOrNull = " + currentUserOrNull);
        System.out.println("hasCurrentUser = " + hasCurrentUser);

        // currentUserOrNull.changeName("Alice"); // NullPointerException 발생
    }

    private static void demonstrateObjectAndContentComparison() {
        printSectionTitle("같은 객체인가, 같은 내용인가");

        int[] firstScores = { 90, 80, 100 };
        int[] secondScores = { 90, 80, 100 };

        boolean isSameScoreObject = firstScores == secondScores;
        boolean isSameScoreContent = Arrays.equals(firstScores, secondScores);

        System.out.println("firstScores == secondScores: " + isSameScoreObject);
        System.out.println("Arrays.equals(firstScores, secondScores): " + isSameScoreContent);

        // 참조 비교 결과를 분명히 보이기 위해 서로 다른 String 객체를 만든다.
        String firstName = new String("Alice");
        String secondName = new String("Alice");

        boolean isSameNameObject = firstName == secondName;
        boolean isSameNameContent = firstName.equals(secondName);

        System.out.println("firstName == secondName: " + isSameNameObject);
        System.out.println("firstName.equals(secondName): " + isSameNameContent);
    }

    private static void demonstratePrimitiveParameterPassing() {
        printSectionTitle("기본 자료형 매개 변수 전달");

        int playerHealth = 10;
        int healingAmount = 50;

        addHealth(playerHealth, healingAmount);
        System.out.println("addHealth 호출 후 playerHealth = " + playerHealth);

        playerHealth = calculateHealedHealth(playerHealth, healingAmount);
        System.out.println("반환값 대입 후 playerHealth = " + playerHealth);
    }

    private static void addHealth(int currentHealth, int healingAmount) {
        currentHealth = currentHealth + healingAmount;
    }

    private static int calculateHealedHealth(int currentHealth, int healingAmount) {
        return currentHealth + healingAmount;
    }

    private static void demonstrateReferenceParameterPassing() {
        printSectionTitle("참조 자료형 매개 변수 전달");

        User alice = new User("Alice", 10);
        User bob = new User("Bob", 99);

        changeLevel(alice, 20);
        tryReplaceUser(alice, bob);

        System.out.println("alice = " + alice);
        System.out.println("bob = " + bob);
    }

    private static void changeLevel(User user, int newLevel) {
        assert (user != null) : "user는 null이면 안 됩니다.";
        user.changeLevel(newLevel);
    }

    private static void tryReplaceUser(User user, User replacementUser) {
        assert (user != null) : "user는 null이면 안 됩니다.";
        assert (replacementUser != null) : "replacementUser는 null이면 안 됩니다.";

        user = replacementUser;
        System.out.println("tryReplaceUser 내부 user = " + user);
    }

    private static void printSectionTitle(String title) {
        System.out.println();
        System.out.println("[" + title + "]");
        System.out.println("--------------------------------------------------");
    }
}
