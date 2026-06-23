public class Slide21PrivateMemberUsage {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(5000);

        account.deposit(3000);
        int currentBalance = account.getBalance();

        // private 접근 제한으로 컴파일되지 않음: account.balance = 0;
        System.out.println("currentBalance = " + currentBalance);
    }
}
