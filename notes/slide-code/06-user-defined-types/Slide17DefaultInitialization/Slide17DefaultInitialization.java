public class Slide17DefaultInitialization {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        /* balance = 0, isLocked = false로 자동 초기화됨
           의도한 초기 상태가 다르다면 별도 초기화가 필요함 */
        System.out.println("balance = " + account.getBalance());
        System.out.println("isLocked = " + account.isLocked());
    }
}
