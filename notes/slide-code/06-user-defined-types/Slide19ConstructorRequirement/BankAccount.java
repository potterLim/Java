class BankAccount {
    private int balance;

    // 생성자를 하나라도 정의하면 기본 생성자는 자동으로 제공되지 않는다.
    BankAccount(int initialBalance) {
        balance = initialBalance;
    }

    int getBalance() {
        return balance;
    }
}
