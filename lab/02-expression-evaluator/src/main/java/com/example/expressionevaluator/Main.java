package com.example.expressionevaluator;

public class Main {
    private static int sPassedCount;
    private static int sFailedCount;
    private static String sFailureMessage;

    private Main() {
    }

    public static void main(String[] args) {
        verifyValidExpressions();
        verifyInvalidExpressions();

        printSummary();
    }

    private static void verifyValidExpressions() {
        verifyScenario(
                "올바른 산술식 계산",
                checkEquals("숫자 하나만 있는 식", "7", ExpressionEvaluator.evaluateOrNull("7"))
                        && checkEquals("음수 하나만 있는 식", "-7", ExpressionEvaluator.evaluateOrNull("-7"))
                        && checkEquals("int 최솟값", "-2147483648", ExpressionEvaluator.evaluateOrNull("-2147483648"))
                        && checkEquals("식 안의 int 최솟값", "-2147483648", ExpressionEvaluator.evaluateOrNull("-2147483648 + 0"))
                        && checkEquals("공백이 포함된 덧셈", "30", ExpressionEvaluator.evaluateOrNull("  10  +   20 "))
                        && checkEquals("연산자 우선순위", "14", ExpressionEvaluator.evaluateOrNull("2 + 3 * 4"))
                        && checkEquals("같은 우선순위의 왼쪽부터 계산", "12", ExpressionEvaluator.evaluateOrNull("20 / 3 * 2"))
                        && checkEquals("연산자 뒤의 음수", "13", ExpressionEvaluator.evaluateOrNull("10 - -3"))
                        && checkEquals("여러 연산자가 섞인 식", "21", ExpressionEvaluator.evaluateOrNull("2 + 3 * 7 - 2"))
        );
    }

    private static void verifyInvalidExpressions() {
        verifyScenario(
                "잘못된 산술식은 null 반환",
                checkNull("식이 null인 경우", ExpressionEvaluator.evaluateOrNull(null))
                        && checkNull("빈 문자열", ExpressionEvaluator.evaluateOrNull(""))
                        && checkNull("공백만 있는 문자열", ExpressionEvaluator.evaluateOrNull("   "))
                        && checkNull("단항 양수 부호", ExpressionEvaluator.evaluateOrNull("+7"))
                        && checkNull("음수 부호 뒤의 공백", ExpressionEvaluator.evaluateOrNull("- 7"))
                        && checkNull("마지막에 남은 연산자", ExpressionEvaluator.evaluateOrNull("1 +"))
                        && checkNull("숫자 사이의 공백", ExpressionEvaluator.evaluateOrNull("1 2 + 3"))
                        && checkNull("연속된 연산자", ExpressionEvaluator.evaluateOrNull("2 * * 3"))
                        && checkNull("선행 0이 있는 숫자", ExpressionEvaluator.evaluateOrNull("01 + 2"))
                        && checkNull("0으로 나누는 식", ExpressionEvaluator.evaluateOrNull("4 / 0"))
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
