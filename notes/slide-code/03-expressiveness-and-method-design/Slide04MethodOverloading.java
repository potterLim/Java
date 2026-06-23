public class Slide04MethodOverloading {
    public static void main(String[] args) {
        notifyUser("Server started");
        notifyUser("WARN", "Disk usage is high");
        notifyUser("ERROR", "Payment failed", 1);
    }

    public static void notifyUser(String message) {
        System.out.println("[INFO] " + message);
    }

    public static void notifyUser(String title, String message) {
        System.out.println("[" + title + "] " + message);
    }

    public static void notifyUser(String title, String message, int priority) {
        System.out.println("[URGENT-" + priority + "] " + title + ": " + message);
    }
}
