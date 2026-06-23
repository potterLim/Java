public class Slide07EnumNameAndOrdinal {
    public static void main(String[] args) {
        OrderStatus orderStatus = OrderStatus.CREATED;

        System.out.println("name = " + orderStatus.name());
        System.out.println("ordinal = " + orderStatus.ordinal());
    }
}
