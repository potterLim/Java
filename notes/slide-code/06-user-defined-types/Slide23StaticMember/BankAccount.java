class BankAccount {
    private static int accountCount;

    private int balance;

    BankAccount(int initialBalance) {
        balance = initialBalance;
        accountCount++;
    }

    static int getAccountCount() {
        return accountCount;
    }

    int getBalance() {
        return balance;
    }
}
