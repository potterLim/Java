public class Slide03PrimitiveValueCopy {
    public static void main(String[] args) {
        int originalNumber = 5;
        int copiedNumber = originalNumber;

        System.out.println("[대입 후]");
        System.out.println("originalNumber = " + originalNumber);
        System.out.println("copiedNumber = " + copiedNumber);

        copiedNumber = 3;

        System.out.println();
        System.out.println("[copiedNumber 변경 후]");
        System.out.println("originalNumber = " + originalNumber);
        System.out.println("copiedNumber = " + copiedNumber);
    }
}
