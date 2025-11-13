package library;

public class UserSession {
    private static boolean isAdmin = false;
    private static String username = null;
    private static String password = null;

    public static boolean isAdmin() {
        return isAdmin;
        
    }

    public static void setAdmin(boolean adminStatus) {
        isAdmin = adminStatus;
    }
    public static String getUsername() {
        return username;
    }

    public static void setUsername(String user) {
        username = user;
    }
    public static String getPassword() {
        return password;
    }

    public static void setPassword(String pass) {
        password = pass;
    }

    public static void clearSession() {
        isAdmin = false;
        username = null;
        password=null;
}
}
