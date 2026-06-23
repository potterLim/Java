class BankAccount {
    private int balance;
    private boolean isLocked;

    BankAccount(int initialBalance) {
        balance = initialBalance;
    }

    void lock() {
        isLocked = true;
    }

    void deposit(int depositAmount) {
        if (isLocked || depositAmount <= 0) {
            return;
        }

        balance += depositAmount;
    }

    void withdraw(int withdrawAmount) {
        if (isLocked) {
            return;
        }

        if (withdrawAmount <= 0 || withdrawAmount > balance) {
            return;
        }

        balance -= withdrawAmount;
    }

    int getBalance() {
        return balance;
    }
}
