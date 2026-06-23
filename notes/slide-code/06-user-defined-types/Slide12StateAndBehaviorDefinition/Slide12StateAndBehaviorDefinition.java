public class Slide12StateAndBehaviorDefinition {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(5000);

        account.withdraw(1000);
        account.lock();
        account.withdraw(1000);

        System.out.println("balance = " + account.getBalance());
    }
}
