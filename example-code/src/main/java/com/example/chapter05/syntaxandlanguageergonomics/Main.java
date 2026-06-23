package com.example.chapter05.syntaxandlanguageergonomics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("5장. 문법과 언어 사용성");
        System.out.println("==================================================");

        demonstrateForeachWithArrayList();
        demonstrateForeachWithHashSet();
        demonstrateForeachWithHashMap();
        demonstrateIndexWhenNeeded();
        demonstrateForeachVariableReassignment();
        demonstrateForeachReferenceMutation();
        demonstrateVarBasics();
        demonstrateVarWithForeach();
    }

    private static void demonstrateForeachWithArrayList() {
        printSectionTitle("ArrayList에서 foreach 문 사용");

        ArrayList<String> userNames = new ArrayList<String>();
        userNames.add("Alice");
        userNames.add("Bobby");
        userNames.add("Charlie");

        for (String userName : userNames) {
            System.out.println(userName);
        }
    }

    private static void demonstrateForeachWithHashSet() {
        printSectionTitle("HashSet에서 foreach 문 사용");

        HashSet<String> processedEmails = new HashSet<String>();
        processedEmails.add("alice@example.com");
        processedEmails.add("bobby@example.com");
        processedEmails.add("charlie@example.com");

        for (String email : processedEmails) {
            System.out.println(email);
        }
    }

    private static void demonstrateForeachWithHashMap() {
        printSectionTitle("HashMap에서 foreach 문 사용");

        HashMap<String, Long> lastLoginTimesByUserName = new HashMap<String, Long>();
        lastLoginTimesByUserName.put("alice", 1_735_689_600L);
        lastLoginTimesByUserName.put("bobby", 1_740_830_400L);
        lastLoginTimesByUserName.put("charlie", 1_750_002_600L);

        for (Map.Entry<String, Long> entry : lastLoginTimesByUserName.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    private static void demonstrateIndexWhenNeeded() {
        printSectionTitle("index가 필요할 때는 일반 for 문 사용");

        ArrayList<String> userNames = new ArrayList<String>();
        userNames.add("Alice");
        userNames.add("Bobby");
        userNames.add("Charlie");

        for (int index = 0; index < userNames.size(); ++index) {
            System.out.println(index + "번째 사용자 = " + userNames.get(index));
        }
    }

    private static void demonstrateForeachVariableReassignment() {
        printSectionTitle("foreach 반복 변수에 다른 값 대입하기");

        ArrayList<String> commands = new ArrayList<String>();
        commands.add("start");
        commands.add("stop");
        commands.add("pause");

        for (String command : commands) {
            command = command.toUpperCase();
        }

        System.out.println("commands = " + commands);
    }

    private static void demonstrateForeachReferenceMutation() {
        printSectionTitle("복사된 참조값으로 객체 상태 변경하기");

        ArrayList<MessageCounter> messageCounters = new ArrayList<MessageCounter>();
        messageCounters.add(new MessageCounter("INFO"));
        messageCounters.add(new MessageCounter("WARN"));

        for (MessageCounter messageCounter : messageCounters) {
            messageCounter.increaseCount();
            messageCounter.increaseCount();
        }

        System.out.println("messageCounters = " + messageCounters);
    }

    private static void demonstrateVarBasics() {
        printSectionTitle("var 기본 사용");

        var userName = "Alice";
        var userCount = 3;
        var scores = new int[] { 90, 80, 100 };

        System.out.println("userName = " + userName);
        System.out.println("userCount = " + userCount);
        System.out.println("scoreCount = " + scores.length);

        // var는 초기화 식이 필요하며 추론된 자료형은 이후에 바뀌지 않는다.
        // var displayName;
        // userCount = "three";
    }

    private static void demonstrateVarWithForeach() {
        printSectionTitle("foreach 문에서 var 사용");

        ArrayList<String> userNames = new ArrayList<String>();
        userNames.add("Alice");
        userNames.add("Bobby");
        userNames.add("Charlie");

        for (var userName : userNames) {
            System.out.println(userName);
        }

        HashMap<String, Integer> loginCountsByUserName = new HashMap<String, Integer>();
        loginCountsByUserName.put("alice", 3);
        loginCountsByUserName.put("bobby", 5);

        for (var entry : loginCountsByUserName.entrySet()) {
            System.out.println(entry.getKey() + " 로그인 횟수 = " + entry.getValue());
        }
    }

    private static void printSectionTitle(String title) {
        System.out.println();
        System.out.println("[" + title + "]");
        System.out.println("--------------------------------------------------");
    }
}
