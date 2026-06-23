public class Slide06DangerousOverloading {
    public static void main(String[] args) {
        int price = 100000;
        int discountPercent = 15; // 15% 할인 의도

        int finalPrice = applyDiscount(discountPercent, price);

        System.out.println("finalPrice = " + finalPrice);
    }

    public static int applyDiscount(double discountPercent, int price) {
        return (int) (price - (price * discountPercent / 100));
    }

    public static int applyDiscount(int discountAmount, int price) {
        return price - discountAmount;
    }
}
