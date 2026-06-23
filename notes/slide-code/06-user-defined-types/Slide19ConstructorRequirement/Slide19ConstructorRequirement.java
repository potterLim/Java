public class Slide19ConstructorRequirement {
    public static void main(String[] args) {
        // 기본 생성자가 없으므로 컴파일되지 않음: BankAccount account = new BankAccount();

        BankAccount account = new BankAccount(5000);

        System.out.println("balance = " + account.getBalance());
    }
}
