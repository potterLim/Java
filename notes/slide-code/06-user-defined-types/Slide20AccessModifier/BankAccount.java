class BankAccount {
    private int balance;

    public BankAccount(int initialBalance) {
        balance = initialBalance;
    }

    public void deposit(int depositAmount) {
        if (depositAmount <= 0) {
            return;
        }

        balance += depositAmount;
    }
}
