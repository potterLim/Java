public class Slide04ReferenceSharing {
    public static void main(String[] args) {
        String[][] memberNames = new String[3][];
        memberNames[0] = new String[11];

        String[] firstTeamMembers = memberNames[0];

        System.out.println("[참조 대입 후]");
        System.out.println("memberNames[0][0] = " + memberNames[0][0]);
        System.out.println("firstTeamMembers[0] = " + firstTeamMembers[0]);

        firstTeamMembers[0] = "Alice";

        System.out.println();
        System.out.println("[firstTeamMembers 변경 후]");
        System.out.println("memberNames[0][0] = " + memberNames[0][0]);
        System.out.println("firstTeamMembers[0] = " + firstTeamMembers[0]);

        memberNames[0][1] = "Bob";

        System.out.println();
        System.out.println("[memberNames 변경 후]");
        System.out.println("memberNames[0][1] = " + memberNames[0][1]);
        System.out.println("firstTeamMembers[1] = " + firstTeamMembers[1]);
    }
}
