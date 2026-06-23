class BankAccount {
    private int balance;

    BankAccount() {
        balance = 0;
    }

    BankAccount(int initialBalance) {
        balance = initialBalance;
    }

    int getBalance() {
        return balance;
    }
}
