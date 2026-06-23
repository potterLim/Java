package com.example.chapter04.managingdatawithcollections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        System.out.println("4장. 컬렉션을 활용한 데이터 관리");
        System.out.println("==================================================");

        demonstrateWrapperTypes();
        demonstrateArrayListOperations();
        demonstrateHashSetOperations();
        demonstrateHashMapOperations();
        demonstrateAdditionalCollectionMethods();
        demonstrateOtherCollectionTypes();
    }

    private static void demonstrateWrapperTypes() {
        printSectionTitle("래퍼 자료형");

        ArrayList<Integer> scores = new ArrayList<Integer>();

        scores.add(90);
        scores.add(85);
        scores.add(100);

        int firstScore = scores.get(0);
        int totalScore = 0;

        for (int i = 0; i < scores.size(); ++i) {
            totalScore += scores.get(i);
        }

        System.out.println("scores = " + scores);
        System.out.println("firstScore = " + firstScore);
        System.out.println("totalScore = " + totalScore);
    }

    private static void demonstrateArrayListOperations() {
        printSectionTitle("ArrayList 주요 동작");

        ArrayList<Integer> searchResultIds = new ArrayList<Integer>();

        boolean isAdded = searchResultIds.add(101);
        searchResultIds.add(101);
        searchResultIds.add(309);
        searchResultIds.add(1, 150);

        int selectedResultId = searchResultIds.get(1);
        int replacedResultId = searchResultIds.set(1, 210);
        int removedResultId = searchResultIds.remove(0);
        int searchResultCount = searchResultIds.size();
        boolean hasResultId = searchResultIds.contains(309);
        int resultIndex = searchResultIds.indexOf(309);

        System.out.println("첫 번째 add 반환값 = " + isAdded);
        System.out.println("get(1) 결과 selectedResultId = " + selectedResultId);
        System.out.println("set(1, 210) 반환값 replacedResultId = " + replacedResultId);
        System.out.println("remove(0) 반환값 removedResultId = " + removedResultId);
        System.out.println("최종 searchResultIds = " + searchResultIds);
        System.out.println("searchResultCount = " + searchResultCount);
        System.out.println("hasResultId = " + hasResultId);
        System.out.println("resultIndex = " + resultIndex);
    }

    private static void demonstrateHashSetOperations() {
        printSectionTitle("HashSet 주요 동작");

        HashSet<String> processedUsers = new HashSet<String>();

        boolean isNewUser = processedUsers.add("Alice");
        System.out.println("Alice 추가 결과 = " + isNewUser);

        isNewUser = processedUsers.add("Alice");
        System.out.println("Alice 다시 추가 결과 = " + isNewUser);

        isNewUser = processedUsers.add("Bobby");
        System.out.println("Bobby 추가 결과 = " + isNewUser);

        boolean isProcessed = processedUsers.contains("Bobby");
        boolean isRemoved = processedUsers.remove("Alice");
        int processedUserCount = processedUsers.size();

        System.out.println("processedUsers = " + processedUsers);
        System.out.println("isProcessed = " + isProcessed);
        System.out.println("isRemoved = " + isRemoved);
        System.out.println("processedUserCount = " + processedUserCount);
    }

    private static void demonstrateHashMapOperations() {
        printSectionTitle("HashMap 주요 동작");

        HashMap<Integer, String> studentNamesById = new HashMap<Integer, String>();

        String firstPreviousStudentNameOrNull = studentNamesById.put(1001, "Alice");
        studentNamesById.put(1002, "Alice");
        String replacedStudentName = studentNamesById.put(1002, "Bobby");

        String selectedStudentName = studentNamesById.get(1002);
        boolean hasStudentId = studentNamesById.containsKey(1003);
        String removedStudentName = studentNamesById.remove(1001);
        int studentCount = studentNamesById.size();

        System.out.println("studentNamesById = " + studentNamesById);
        System.out.println("첫 번째 put 반환값 firstPreviousStudentNameOrNull = " + firstPreviousStudentNameOrNull);
        System.out.println("교체 put 반환값 replacedStudentName = " + replacedStudentName);
        System.out.println("selectedStudentName = " + selectedStudentName);
        System.out.println("hasStudentId = " + hasStudentId);
        System.out.println("removedStudentName = " + removedStudentName);
        System.out.println("studentCount = " + studentCount);
    }

    private static void demonstrateAdditionalCollectionMethods() {
        printSectionTitle("추가로 알아두면 좋은 컬렉션 메서드");

        ArrayList<String> todoItems = new ArrayList<String>();
        todoItems.add("우유 사기");
        todoItems.add("과제 제출하기");

        boolean hasAssignment = todoItems.contains("과제 제출하기");
        boolean isEmptyBeforeClear = todoItems.isEmpty();
        todoItems.clear();
        boolean isEmptyAfterClear = todoItems.isEmpty();

        System.out.println("hasAssignment = " + hasAssignment);
        System.out.println("isEmptyBeforeClear = " + isEmptyBeforeClear);
        System.out.println("isEmptyAfterClear = " + isEmptyAfterClear);

        HashMap<Integer, String> studentNamesById = new HashMap<Integer, String>();
        studentNamesById.put(1001, "Alice");
        studentNamesById.put(1002, null);

        String missingStudentName = studentNamesById.getOrDefault(9999, "알 수 없음");
        boolean hasNullMappedStudentId = studentNamesById.containsKey(1002);
        String nullMappedStudentNameOrNull = studentNamesById.get(1002);

        studentNamesById.putIfAbsent(1001, "Changed");
        studentNamesById.putIfAbsent(1003, "Charlie");

        System.out.println("missingStudentName = " + missingStudentName);
        System.out.println("hasNullMappedStudentId = " + hasNullMappedStudentId);
        System.out.println("nullMappedStudentNameOrNull = " + nullMappedStudentNameOrNull);
        System.out.println("studentNamesById = " + studentNamesById);
    }

    private static void demonstrateOtherCollectionTypes() {
        printSectionTitle("목적에 맞는 다른 컬렉션");

        Queue<String> waitingJobs = new ArrayDeque<String>();
        waitingJobs.add("compile");
        waitingJobs.add("test");
        waitingJobs.add("package");

        String nextJob = waitingJobs.poll();

        System.out.println("nextJob = " + nextJob);
        System.out.println("waitingJobs = " + waitingJobs);

        LinkedList<String> recentMessages = new LinkedList<String>();
        recentMessages.add("첫 번째 메시지");
        recentMessages.addFirst("가장 앞에 추가한 메시지");
        recentMessages.addLast("가장 뒤에 추가한 메시지");

        System.out.println("recentMessages = " + recentMessages);
    }

    private static void printSectionTitle(String title) {
        System.out.println();
        System.out.println("[" + title + "]");
        System.out.println("--------------------------------------------------");
    }
}
