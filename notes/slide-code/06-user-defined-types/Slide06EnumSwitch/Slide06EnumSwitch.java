public class Slide06EnumSwitch {
    public static void main(String[] args) {
        OrderStatus orderStatus = OrderStatus.PAID;
        String orderStatusMessage = "";

        switch (orderStatus) {
            case CREATED:
                orderStatusMessage = "Order created";
                break;
            case PAID:
                orderStatusMessage = "Payment completed";
                break;
            case SHIPPED:
                orderStatusMessage = "Order shipped";
                break;
            default:
                assert (false) : "Unexpected order status: " + orderStatus;
                break;
        }

        System.out.println(orderStatusMessage);
    }
}
