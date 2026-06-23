public class Slide14ObjectReferenceSharing {
    public static void main(String[] args) {
        BankAccount primaryAccount = new BankAccount();
        BankAccount aliasAccount = primaryAccount;

        aliasAccount.deposit(1000);

        System.out.println("primaryAccount balance = " + primaryAccount.getBalance());
        System.out.println("aliasAccount balance = " + aliasAccount.getBalance());
    }
}
