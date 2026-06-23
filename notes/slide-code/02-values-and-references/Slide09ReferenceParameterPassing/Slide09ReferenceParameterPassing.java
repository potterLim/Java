public class Slide09ReferenceParameterPassing {
    public static void main(String[] args) {
        User alice = new User("Alice", 10);
        User bob = new User("Bob", 99);

        changeLevel(alice, 20);
        tryReplaceUser(alice, bob);

        System.out.println("alice = " + alice);
        System.out.println("bob = " + bob);
    }

    public static void changeLevel(User user, int newLevel) {
        user.changeLevel(newLevel);
    }

    public static void tryReplaceUser(User user, User replacementUser) {
        user = replacementUser;
    }
}
