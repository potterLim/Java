public class Slide09StringBuilderUsage {
    public static void main(String[] args) {
        StringBuilder messageBuilder = new StringBuilder(128);

        messageBuilder.append("Why not ");
        messageBuilder.append("change ");
        messageBuilder.append("the world?");

        String message = messageBuilder.toString();

        System.out.println(message);
    }
}
