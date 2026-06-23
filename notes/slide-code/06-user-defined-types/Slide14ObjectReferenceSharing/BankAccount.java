class BankAccount {
    private int balance;

    void deposit(int depositAmount) {
        if (depositAmount <= 0) {
            return;
        }

        balance += depositAmount;
    }

    int getBalance() {
        return balance;
    }
}
