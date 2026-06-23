package com.example.spaceconvoy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    private static int sPassedCount;
    private static int sFailedCount;
    private static String sFailureMessage;

    private Main() {
    }

    public static void main(String[] args) {
        verifyShipBehavior();
        verifyConvoyLaneBehavior();

        printSummary();
    }

    private static void verifyShipBehavior() {
        Ship ship = new Ship("Ship", EHullGrade.MEDIUM, 40, 14, 8, 3);

        ship.applyHullChange(6);
        boolean isPassed = checkEquals("내구도 증가", 46, ship.getHull());

        ship.applyHullChange(-100);
        isPassed = isPassed && checkEquals("내구도 하한", 0, ship.getHull());

        Ship defender = new Ship("Defender", EHullGrade.HEAVY, 24, 11, 6, 4);
        Ship attacker = new Ship("Attacker", EHullGrade.MEDIUM, 28, 15, 10, 3);

        attacker.fireLaser(defender);
        isPassed = isPassed && checkEquals("방패와 선체 등급 반영", 17, defender.getHull());

        defender.repair();
        isPassed = isPassed && checkEquals("수리 후 내구도", 21, defender.getHull());

        Ship armoredDefender = new Ship("Armored", EHullGrade.ARMORED, 10, 0, 1, 0);
        Ship weakAttacker = new Ship("Weak", EHullGrade.LIGHT, 10, 2, 0, 0);
        weakAttacker.fireLaser(armoredDefender);
        isPassed = isPassed && checkEquals("0이 아닌 최소 피해", 9, armoredDefender.getHull());

        Ship shieldedDefender = new Ship("Shielded", EHullGrade.MEDIUM, 10, 0, 5, 0);
        Ship blockedAttacker = new Ship("Blocked", EHullGrade.MEDIUM, 10, 5, 0, 0);
        blockedAttacker.fireLaser(shieldedDefender);
        isPassed = isPassed && checkEquals("방패로 피해 차단", 10, shieldedDefender.getHull());

        verifyScenario("우주선의 내구도, 피해, 수리 규칙", isPassed);
    }

    private static void verifyConvoyLaneBehavior() {
        Path shipFilePathOrNull = null;

        try {
            shipFilePathOrNull = Files.createTempFile("space-convoy-main-", ".csv");
            Files.writeString(
                    shipFilePathOrNull,
                    String.join(
                            "\n",
                            "Falcon,MEDIUM,32,14,8,3",
                            "Vanguard,HEAVY,38,16,10,2",
                            "Swift,LIGHT,26,18,5,2",
                            "Bulwark,ARMORED,45,12,14,1",
                            "Raven,MEDIUM,30,15,7,3"
                    )
            );

            ConvoyLane lane = new ConvoyLane("Outer Rim Convoy", 5);
            boolean isPassed = checkNull("비어 있는 항로", lane.predictIncomingDamageOrNull());

            lane.loadShips(shipFilePathOrNull.toString());
            isPassed = isPassed && checkArrayEquals(
                    "예상 피해",
                    new int[] { 8, 6, 8, 1, 7 },
                    lane.predictIncomingDamageOrNull()
            );

            lane.advanceTurn();
            isPassed = isPassed && checkEquals("턴 수", 1, lane.getTurns());
            isPassed = isPassed && checkArrayEquals(
                    "턴 진행 후 우주선 순서",
                    new int[] { 8, 6, 8, 1, 7 },
                    lane.predictIncomingDamageOrNull()
            );

            ConvoyLane limitedLane = new ConvoyLane("Limited Convoy", 3);
            limitedLane.loadShips(shipFilePathOrNull.toString());
            isPassed = isPassed && checkArrayEquals(
                    "항로 수용량 제한",
                    new int[] { 8, 6, 11 },
                    limitedLane.predictIncomingDamageOrNull()
            );

            ConvoyLane singleShipLane = new ConvoyLane("Single Ship Convoy", 1);
            singleShipLane.loadShips(shipFilePathOrNull.toString());
            isPassed = isPassed && checkArrayEquals(
                    "우주선이 하나인 경우의 예상 피해",
                    new int[] { 0 },
                    singleShipLane.predictIncomingDamageOrNull()
            );

            limitedLane.loadShips("   ");
            isPassed = isPassed && checkNull("잘못된 파일 경로 입력 후 항로 초기화", limitedLane.predictIncomingDamageOrNull());

            verifyScenario("항로의 우주선 로드와 예상 피해 계산", isPassed);
        } catch (IOException exception) {
            fail("항로 파일 검증 준비", exception.getMessage());
        } finally {
            deletePathIfExists(shipFilePathOrNull);
        }

        verifyDestroyedShipsAreRemovedAfterLaserPhase();
    }

    private static void verifyDestroyedShipsAreRemovedAfterLaserPhase() {
        Path shipFilePathOrNull = null;

        try {
            shipFilePathOrNull = Files.createTempFile("space-convoy-main-destroyed-", ".csv");
            Files.writeString(
                    shipFilePathOrNull,
                    String.join(
                            "\n",
                            "Alpha,LIGHT,3,10,0,5",
                            "Beta,MEDIUM,20,10,0,0"
                    )
            );

            ConvoyLane lane = new ConvoyLane("Removal Convoy", 2);
            lane.loadShips(shipFilePathOrNull.toString());

            boolean isPassed = checkArrayEquals(
                    "초기 예상 피해",
                    new int[] { 12, 10 },
                    lane.predictIncomingDamageOrNull()
            );

            lane.advanceTurn();

            isPassed = isPassed && checkEquals("제거 후 턴 수", 1, lane.getTurns())
                    && checkArrayEquals("파괴된 우주선 제거", new int[] { 0 }, lane.predictIncomingDamageOrNull());

            verifyScenario("수리 단계 전에 파괴된 우주선 제거", isPassed);
        } catch (IOException exception) {
            fail("파괴된 우주선 제거 검증 준비", exception.getMessage());
        } finally {
            deletePathIfExists(shipFilePathOrNull);
        }
    }

    private static boolean checkEquals(String name, int expected, int actual) {
        if (expected == actual) {
            return true;
        }

        sFailureMessage = name + ": 기대=" + expected + ", 실제=" + actual;
        return false;
    }

    private static boolean checkArrayEquals(String name, int[] expected, int[] actualOrNull) {
        if (actualOrNull == null) {
            sFailureMessage = name + ": 배열을 기대했지만 null을 반환함";
            return false;
        }

        if (expected.length != actualOrNull.length) {
            sFailureMessage = name + ": 기대 길이=" + expected.length + ", 실제 길이=" + actualOrNull.length;
            return false;
        }

        for (int i = 0; i < expected.length; ++i) {
            if (expected[i] != actualOrNull[i]) {
                sFailureMessage = name + ": 인덱스=" + i + ", 기대=" + expected[i] + ", 실제=" + actualOrNull[i];
                return false;
            }
        }

        return true;
    }

    private static boolean checkNull(String name, int[] actualOrNull) {
        if (actualOrNull == null) {
            return true;
        }

        sFailureMessage = name + ": 기대=null";
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

    private static void deletePathIfExists(Path pathOrNull) {
        if (pathOrNull == null) {
            return;
        }

        try {
            Files.deleteIfExists(pathOrNull);
        } catch (IOException ignored) {
        }
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
