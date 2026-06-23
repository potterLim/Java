package com.example.chapter06.userdefinedtypes;

public class Main {
    public static void main(String[] args) {
        System.out.println("6장. 사용자 정의 자료형");
        System.out.println("==================================================");

        demonstratePrimitiveConstantsProblem();
        demonstrateEnumTypeSafety();
        demonstrateEnumSwitch();
        demonstrateEnumNameAndOrdinal();
        demonstrateClassStateAndBehavior();
        demonstrateObjectReferenceSharing();
        demonstrateDefaultValues();
        demonstrateConstructors();
        demonstrateAccessModifiers();
        demonstrateStaticMembers();
    }

    private static void demonstratePrimitiveConstantsProblem() {
        printSectionTitle("정수 상수만 사용할 때 생기는 문제");

        int downloadStatus = 999;
        long downloadedBytes = -50;
        long totalBytes = 10_000L;

        System.out.println("downloadStatus = " + downloadStatus);
        System.out.println("downloadedBytes = " + downloadedBytes);
        System.out.println("totalBytes = " + totalBytes);
    }

    private static void demonstrateEnumTypeSafety() {
        printSectionTitle("enum과 타입 안정성");

        EDownloadStatus downloadStatus = EDownloadStatus.QUEUED;
        EPaymentStatus paymentStatus = EPaymentStatus.COMPLETED;

        System.out.println("downloadStatus = " + downloadStatus);
        System.out.println("paymentStatus = " + paymentStatus);

        // downloadStatus = paymentStatus; // 컴파일 에러
    }

    private static void demonstrateEnumSwitch() {
        printSectionTitle("enum과 switch 문");

        EDownloadStatus downloadStatus = EDownloadStatus.DOWNLOADING;
        String statusMessage = buildDownloadStatusMessage(downloadStatus);

        System.out.println(statusMessage);
    }

    private static String buildDownloadStatusMessage(EDownloadStatus downloadStatus) {
        switch (downloadStatus) {
            case QUEUED:
                return "작업이 대기 중입니다.";
            case DOWNLOADING:
                return "작업을 내려받는 중입니다.";
            case COMPLETED:
                return "작업이 완료되었습니다.";
            case FAILED:
                return "작업이 실패했습니다.";
            case CANCELED:
                return "작업이 취소되었습니다.";
            default:
                assert (false) : "예상하지 못한 다운로드 상태입니다: " + downloadStatus;
                return "";
        }
    }

    private static void demonstrateEnumNameAndOrdinal() {
        printSectionTitle("enum의 name과 ordinal");

        EDownloadStatus downloadStatus = EDownloadStatus.QUEUED;

        System.out.println("name = " + downloadStatus.name());
        System.out.println("ordinal = " + downloadStatus.ordinal());
    }

    private static void demonstrateClassStateAndBehavior() {
        printSectionTitle("상태와 동작을 함께 묶는 class");

        DownloadTask task = new DownloadTask("notes.pdf", 10_000L);
        DownloadTask canceledTask = new DownloadTask("old.zip", 1_000L);

        task.start();
        task.addProgress(3_000L);
        task.addProgress(7_000L);
        canceledTask.cancel();

        System.out.println("fileName = " + task.getFileName());
        System.out.println("status = " + task.getStatus());
        System.out.println("progressPercent = " + task.getProgressPercent());
        System.out.println("isCompleted = " + task.isCompleted());
        System.out.println("canceledTask status = " + canceledTask.getStatus());
    }

    private static void demonstrateObjectReferenceSharing() {
        printSectionTitle("객체는 참조형이다");

        DownloadTask primaryTask = new DownloadTask("slide.pptx", 25_000L);
        DownloadTask aliasTask = primaryTask;

        aliasTask.start();
        aliasTask.addProgress(5_000L);

        System.out.println("primaryTask downloadedBytes = " + primaryTask.getDownloadedBytes());
        System.out.println("aliasTask downloadedBytes = " + aliasTask.getDownloadedBytes());

        primaryTask = null;
        System.out.println("primaryTask가 null인가? " + (primaryTask == null));

        // primaryTask.start(); // NullPointerException 발생
    }

    private static void demonstrateDefaultValues() {
        printSectionTitle("멤버 변수의 기본값");

        UserProfile profile = new UserProfile();

        System.out.println(profile.buildSummary());
        System.out.println("nicknameOrNull = " + profile.getNicknameOrNull());
        System.out.println("tierOrNull = " + profile.getTierOrNull());
    }

    private static void demonstrateConstructors() {
        printSectionTitle("생성자와 생성자 오버로딩");

        UserProfile guestProfile = new UserProfile("guest");
        UserProfile subscriberProfile = new UserProfile("alice", ESubscriptionTier.PRO);

        boolean isNicknameChanged = guestProfile.changeNickname("guest-user");
        guestProfile.upgradeToPro();

        System.out.println("닉네임 변경 결과 = " + isNicknameChanged);
        System.out.println(guestProfile.buildSummary());
        System.out.println(subscriberProfile.buildSummary());
    }

    private static void demonstrateAccessModifiers() {
        printSectionTitle("접근 제어자와 public 메서드");

        DownloadTask task = new DownloadTask("video.mp4", 100_000L);

        // task.mDownloadedBytes = 123L; // 컴파일 에러

        task.start();
        task.addProgress(50_000L);

        long progressSnapshot = task.getDownloadedBytes();

        System.out.println("progressSnapshot = " + progressSnapshot);
    }

    private static void demonstrateStaticMembers() {
        printSectionTitle("static 멤버는 클래스에 속한다");

        int createdTaskCountBefore = DownloadTask.getCreatedTaskCount();

        DownloadTask firstTask = new DownloadTask("a.txt", 10L);
        DownloadTask secondTask = new DownloadTask("b.txt", 20L);

        int createdTaskCountAfter = DownloadTask.getCreatedTaskCount();

        System.out.println("createdTaskCountBefore = " + createdTaskCountBefore);
        System.out.println("createdTaskCountAfter = " + createdTaskCountAfter);
        System.out.println("이 섹션에서 생성한 작업 수 = " + (createdTaskCountAfter - createdTaskCountBefore));
        System.out.println("firstTask id = " + firstTask.getId());
        System.out.println("secondTask id = " + secondTask.getId());
    }

    private static void printSectionTitle(String title) {
        System.out.println();
        System.out.println("[" + title + "]");
        System.out.println("--------------------------------------------------");
    }
}
