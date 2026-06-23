package com.example.lineardatastructures;

public class Main {
    private static int sPassedCount;
    private static int sFailedCount;
    private static String sFailureMessage;

    private Main() {
    }

    public static void main(String[] args) {
        verifyList();
        verifyStack();
        verifyQueue();
        verifyLinkedList();

        printSummary();
    }

    private static void verifyList() {
        List list = new List();

        boolean isPassed = checkTrue("새 List는 비어 있음", list.isEmpty())
                && checkEquals("새 List의 내부 저장 공간 크기", 0, list.getCapacity())
                && checkTrue("첫 번째 값 추가", list.add(5))
                && checkTrue("두 번째 값 추가", list.add(7))
                && checkTrue("세 번째 값 추가", list.add(9))
                && checkTrue("네 번째 값 추가", list.add(11))
                && checkEquals("첫 번째 내부 저장 공간 확장", 4, list.getCapacity())
                && checkTrue("다섯 번째 값 추가", list.add(13))
                && checkEquals("두 번째 내부 저장 공간 확장", 8, list.getCapacity())
                && checkTrue("중간 위치 삽입", list.insert(2, 8))
                && checkFalse("잘못된 삽입 거부", list.insert(7, 99))
                && checkValues("삽입 후 값", list, new int[] { 5, 7, 8, 9, 11, 13 })
                && checkTrue("중간 값 변경", list.set(2, 80))
                && checkFalse("잘못된 변경 거부", list.set(6, 99))
                && checkTrue("첫 번째 값 제거", list.removeAt(0))
                && checkTrue("마지막 값 제거", list.removeAt(4))
                && checkFalse("잘못된 제거 거부", list.removeAt(-1))
                && checkValues("제거 후 값", list, new int[] { 7, 80, 9, 11 });

        if (isPassed) {
            list.clear();
            isPassed = checkTrue("clear 후 List가 비어 있음", list.isEmpty())
                    && checkEquals("clear 후 내부 저장 공간 유지", 8, list.getCapacity());
        }

        verifyScenario("배열 기반 List의 순서와 내부 저장 공간 규칙", isPassed);
    }

    private static void verifyStack() {
        Stack stack = new Stack(2);
        IntValue outValue = new IntValue();

        boolean isPassed = checkTrue("새 Stack은 비어 있음", stack.isEmpty())
                && checkEquals("초기 내부 저장 공간 크기", 2, stack.getCapacity())
                && checkTrue("첫 번째 값 push", stack.push(5))
                && checkTrue("두 번째 값 push", stack.push(7))
                && checkTrue("세 번째 값 push", stack.push(9))
                && checkEquals("내부 저장 공간 확장", 4, stack.getCapacity())
                && checkEquals("push 후 개수", 3, stack.getCount())
                && checkTrue("peek 성공", stack.peek(outValue))
                && checkEquals("peek으로 top 확인", 9, outValue.getValue())
                && checkEquals("peek 후 개수 유지", 3, stack.getCount())
                && checkTrue("top pop", stack.pop(outValue))
                && checkEquals("첫 번째 pop 값", 9, outValue.getValue())
                && checkTrue("다음 값 pop", stack.pop(outValue))
                && checkEquals("두 번째 pop 값", 7, outValue.getValue());

        if (isPassed) {
            stack.clear();
            isPassed = checkTrue("clear 후 Stack이 비어 있음", stack.isEmpty())
                    && checkEquals("clear 후 내부 저장 공간 유지", 4, stack.getCapacity())
                    && checkFalse("빈 Stack에서 pop 거부", stack.pop(outValue))
                    && checkFalse("빈 Stack에서 peek 거부", stack.peek(outValue));
        }

        verifyScenario("Stack의 LIFO와 내부 저장 공간 규칙", isPassed);
    }

    private static void verifyQueue() {
        Queue queue = new Queue(3);
        IntValue outValue = new IntValue();

        boolean isPassed = checkTrue("새 Queue는 비어 있음", queue.isEmpty())
                && checkEquals("초기 내부 저장 공간 크기", 3, queue.getCapacity())
                && checkTrue("첫 번째 값 enqueue", queue.enqueue(5))
                && checkTrue("두 번째 값 enqueue", queue.enqueue(7))
                && checkTrue("세 번째 값 enqueue", queue.enqueue(9))
                && checkEquals("enqueue 후 개수", 3, queue.getCount())
                && checkTrue("peek 성공", queue.peek(outValue))
                && checkEquals("peek으로 front 확인", 5, outValue.getValue())
                && checkTrue("첫 번째 값 dequeue", queue.dequeue(outValue))
                && checkEquals("첫 번째 dequeue 값", 5, outValue.getValue())
                && checkTrue("rear 순환 후 enqueue", queue.enqueue(11))
                && checkTrue("enqueue 중 내부 저장 공간 확장", queue.enqueue(13))
                && checkEquals("내부 저장 공간 확장", 6, queue.getCapacity())
                && checkEquals("확장 후 개수", 4, queue.getCount())
                && checkTrue("두 번째 값 dequeue", queue.dequeue(outValue))
                && checkEquals("두 번째 dequeue 값", 7, outValue.getValue())
                && checkTrue("세 번째 값 dequeue", queue.dequeue(outValue))
                && checkEquals("세 번째 dequeue 값", 9, outValue.getValue())
                && checkTrue("순환 위치의 값 dequeue", queue.dequeue(outValue))
                && checkEquals("순환 위치의 dequeue 값", 11, outValue.getValue())
                && checkTrue("확장 후 추가된 값 dequeue", queue.dequeue(outValue))
                && checkEquals("확장 후 추가된 dequeue 값", 13, outValue.getValue());

        if (isPassed) {
            queue.clear();
            isPassed = checkTrue("clear 후 Queue가 비어 있음", queue.isEmpty())
                    && checkEquals("clear 후 내부 저장 공간 유지", 6, queue.getCapacity())
                    && checkFalse("빈 Queue에서 dequeue 거부", queue.dequeue(outValue))
                    && checkFalse("빈 Queue에서 peek 거부", queue.peek(outValue));
        }

        verifyScenario("Queue의 FIFO, 순환, 내부 저장 공간 규칙", isPassed);
    }

    private static void verifyLinkedList() {
        LinkedList list = new LinkedList();

        boolean isPassed = checkTrue("새 LinkedList는 비어 있음", list.isEmpty())
                && checkTrue("첫 번째 값 추가", list.add(5))
                && checkTrue("두 번째 값 추가", list.add(7))
                && checkTrue("중간 위치 삽입", list.insert(1, 6))
                && checkTrue("맨 앞 삽입", list.insert(0, 4))
                && checkTrue("맨 뒤 삽입", list.insert(4, 9))
                && checkFalse("잘못된 삽입 거부", list.insert(6, 99))
                && checkValues("삽입 후 값", list, new int[] { 4, 5, 6, 7, 9 })
                && checkTrue("중간 값 변경", list.set(2, 60))
                && checkFalse("잘못된 변경 거부", list.set(5, 99))
                && checkTrue("첫 번째 값 제거", list.removeAt(0))
                && checkTrue("마지막 값 제거", list.removeAt(3))
                && checkValues("양끝 제거 후 값", list, new int[] { 5, 60, 7 })
                && checkTrue("중간 값 제거", list.removeAt(1))
                && checkFalse("잘못된 제거 거부", list.removeAt(2))
                && checkValues("중간 제거 후 값", list, new int[] { 5, 7 });

        if (isPassed) {
            list.clear();
            isPassed = checkTrue("clear 후 LinkedList가 비어 있음", list.isEmpty())
                    && checkEquals("clear 후 개수 초기화", 0, list.getCount());
        }

        verifyScenario("LinkedList의 연결과 경계 조건", isPassed);
    }

    private static boolean checkValues(String name, List list, int[] expected) {
        if (!checkEquals(name + " 개수", expected.length, list.getCount())) {
            return false;
        }

        IntValue outValue = new IntValue();

        for (int i = 0; i < expected.length; ++i) {
            if (!list.get(i, outValue)) {
                sFailureMessage = name + ": get 실패, 인덱스=" + i;
                return false;
            }

            if (outValue.getValue() != expected[i]) {
                sFailureMessage = name + ": 인덱스=" + i + ", 기대=" + expected[i] + ", 실제=" + outValue.getValue();
                return false;
            }
        }

        return true;
    }

    private static boolean checkValues(String name, LinkedList list, int[] expected) {
        if (!checkEquals(name + " 개수", expected.length, list.getCount())) {
            return false;
        }

        IntValue outValue = new IntValue();

        for (int i = 0; i < expected.length; ++i) {
            if (!list.get(i, outValue)) {
                sFailureMessage = name + ": get 실패, 인덱스=" + i;
                return false;
            }

            if (outValue.getValue() != expected[i]) {
                sFailureMessage = name + ": 인덱스=" + i + ", 기대=" + expected[i] + ", 실제=" + outValue.getValue();
                return false;
            }
        }

        return true;
    }

    private static boolean checkEquals(String name, int expected, int actual) {
        if (expected == actual) {
            return true;
        }

        sFailureMessage = name + ": 기대=" + expected + ", 실제=" + actual;
        return false;
    }

    private static boolean checkTrue(String name, boolean actual) {
        if (actual) {
            return true;
        }

        sFailureMessage = name + ": 기대=true, 실제=false";
        return false;
    }

    private static boolean checkFalse(String name, boolean actual) {
        if (!actual) {
            return true;
        }

        sFailureMessage = name + ": 기대=false, 실제=true";
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
