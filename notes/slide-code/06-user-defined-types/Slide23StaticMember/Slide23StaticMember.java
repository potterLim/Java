public class Slide23StaticMember {
    public static void main(String[] args) {
        BankAccount primaryAccount = new BankAccount(5000);
        int totalAccountCount = BankAccount.getAccountCount(); // 1

        BankAccount secondaryAccount = new BankAccount(8000);
        totalAccountCount = BankAccount.getAccountCount();     // 2

        System.out.println("primaryAccount balance = " + primaryAccount.getBalance());
        System.out.println("secondaryAccount balance = " + secondaryAccount.getBalance());
        System.out.println("totalAccountCount = " + totalAccountCount);
    }
}
