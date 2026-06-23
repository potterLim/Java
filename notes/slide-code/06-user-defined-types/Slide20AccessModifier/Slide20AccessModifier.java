public class Slide20AccessModifier {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(5000);

        account.deposit(1000);
        // private 접근 제한으로 컴파일되지 않음: account.balance = 0;
    }
}
