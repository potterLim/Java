package com.example.bignumber;

public class Main {
    private static int sPassedCount;
    private static int sFailedCount;
    private static String sFailureMessage;

    private Main() {
    }

    public static void main(String[] args) {
        verifyAdditionCases();
        verifySubtractionCases();
        verifyInvalidInputs();

        printSummary();
    }

    private static void verifyAdditionCases() {
        verifyScenario(
                "덧셈: 부호, 정규화, 큰 수 처리",
                checkEquals("양수 덧셈", "579", BigNumberCalculator.addOrNull("123", "456"))
                        && checkEquals("음수와 양수의 덧셈", "333", BigNumberCalculator.addOrNull("-123", "456"))
                        && checkEquals("양수와 음수의 덧셈", "-333", BigNumberCalculator.addOrNull("123", "-456"))
                        && checkEquals("두 음수의 덧셈", "-579", BigNumberCalculator.addOrNull("-123", "-456"))
                        && checkEquals("선행 0 제거", "123", BigNumberCalculator.addOrNull("0000123", "000"))
                        && checkEquals("음수 0 정규화", "0", BigNumberCalculator.addOrNull("-0000", "0"))
                        && checkEquals("정규화 후 서로 반대인 값", "0", BigNumberCalculator.addOrNull("-0007", "0007"))
                        && checkEquals("큰 수 자리올림", "1000000000000000000000", BigNumberCalculator.addOrNull("999999999999999999999", "1"))
        );
    }

    private static void verifySubtractionCases() {
        verifyScenario(
                "뺄셈: 크기 관계, 부호, 큰 수 처리",
                checkEquals("오른쪽 값이 더 작은 경우", "333", BigNumberCalculator.subtractOrNull("456", "123"))
                        && checkEquals("오른쪽 값이 더 큰 경우", "-333", BigNumberCalculator.subtractOrNull("123", "456"))
                        && checkEquals("음수끼리의 뺄셈", "-333", BigNumberCalculator.subtractOrNull("-456", "-123"))
                        && checkEquals("오른쪽 값이 더 작은 음수인 경우", "333", BigNumberCalculator.subtractOrNull("-123", "-456"))
                        && checkEquals("정규화 후 같은 값", "0", BigNumberCalculator.subtractOrNull("-0007", "-0007"))
                        && checkEquals("큰 수 자리내림", "999999999999999999999", BigNumberCalculator.subtractOrNull("1000000000000000000000", "1"))
        );
    }

    private static void verifyInvalidInputs() {
        verifyScenario(
                "잘못된 정수 문자열은 null 반환",
                checkNull("왼쪽 값이 null인 경우", BigNumberCalculator.addOrNull(null, "1"))
                        && checkNull("오른쪽 값이 null인 경우", BigNumberCalculator.addOrNull("1", null))
                        && checkNull("빈 문자열", BigNumberCalculator.addOrNull("", "1"))
                        && checkNull("부호만 있는 문자열", BigNumberCalculator.addOrNull("-", "1"))
                        && checkNull("양수 부호 사용", BigNumberCalculator.addOrNull("+12", "1"))
                        && checkNull("소수 형태", BigNumberCalculator.addOrNull("12.3", "1"))
                        && checkNull("2진수처럼 보이는 문자열", BigNumberCalculator.subtractOrNull("0b1010", "1"))
        );
    }

    private static boolean checkEquals(String name, String expected, String actual) {
        if (expected.equals(actual)) {
            return true;
        }

        sFailureMessage = name + ": 기대=" + expected + ", 실제=" + actual;
        return false;
    }

    private static boolean checkNull(String name, String actual) {
        if (actual == null) {
            return true;
        }

        sFailureMessage = name + ": 기대=null, 실제=" + actual;
        return false;
    }

    private static void verifyScenario(String name, boolean isPassed) {
        if (isPassed) {
            pass(name);
            return;
        }

        fail(name, sFailureMessage);
        sFailureMessage = null;
    }

    private static void pass(String name) {
        ++sPassedCount;
        System.out.println("[통과] " + name);
    }

    private static void fail(String name, String message) {
        ++sFailedCount;
        System.out.println("[실패] " + name + " (" + message + ")");
    }

    private static void printSummary() {
        System.out.println();
        System.out.println("통과: " + sPassedCount);
        System.out.println("실패: " + sFailedCount);
    }
}
