public class Slide05EnumTypeSafety {
    public static void main(String[] args) {
        PaymentStatus paymentStatus = PaymentStatus.COMPLETED;

        // 서로 다른 enum 자료형이므로 컴파일되지 않음: OrderStatus orderStatus = paymentStatus;
        System.out.println("paymentStatus = " + paymentStatus);
    }
}
