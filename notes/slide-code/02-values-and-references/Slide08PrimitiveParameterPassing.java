public class Slide08PrimitiveParameterPassing {
    public static void main(String[] args) {
        int playerHealth = 10;
        int healingAmount = 50;

        addHealth(playerHealth, healingAmount);

        System.out.println("playerHealth = " + playerHealth);
    }

    public static void addHealth(int currentHealth, int healingAmount) {
        currentHealth = currentHealth + healingAmount;
    }
}
