public class Slide13StringBuilderCapacityGrowth {
    public static void main(String[] args) {
        StringBuilder messageBuilder = new StringBuilder(8);

        messageBuilder.append("Why not ");
        int length = messageBuilder.length();     // 8
        int capacity = messageBuilder.capacity(); // 8

        System.out.println("length = " + length + ", capacity = " + capacity);

        messageBuilder.append("change ");
        length = messageBuilder.length();         // 15
        capacity = messageBuilder.capacity();     // 18

        System.out.println("length = " + length + ", capacity = " + capacity);
    }
}
