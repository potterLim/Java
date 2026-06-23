public class Slide18ConstructorOverloading {
    public static void main(String[] args) {
        BankAccount primaryAccount = new BankAccount();
        BankAccount secondaryAccount = new BankAccount(5000);

        System.out.println("primaryAccount balance = " + primaryAccount.getBalance());
        System.out.println("secondaryAccount balance = " + secondaryAccount.getBalance());
    }
}
