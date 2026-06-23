class BankAccount {
    private int balance;

    public BankAccount(int initialBalance) {
        balance = initialBalance;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int depositAmount) {
        if (depositAmount <= 0) {
            return;
        }

        balance += depositAmount;
    }

    public void withdraw(int withdrawAmount) {
        if (withdrawAmount <= 0 || withdrawAmount > balance) {
            return;
        }

        balance -= withdrawAmount;
    }
}
