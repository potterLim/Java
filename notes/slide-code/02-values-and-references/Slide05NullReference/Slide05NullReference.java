public class Slide05NullReference {
    public static void main(String[] args) {
        User currentUser = null;

        currentUser.changeName("Alice"); // NullPointerException 발생
    }
}
