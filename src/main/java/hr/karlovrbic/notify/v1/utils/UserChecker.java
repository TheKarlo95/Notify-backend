package hr.karlovrbic.notify.v1.utils;

/**
 * Created by Karlo Vrbic on 05.01.17..
 */
public class UserChecker {

    private static final String USERNAME_REGEX = "[a-zA-Z_][a-zA-Z0-9\\._\\-]{3,30}";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

    private UserChecker() {
    }

    public static boolean isValidUserName(String username) {
        return username != null && username.matches(USERNAME_REGEX);
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    public static boolean isValidName(String name) {
        return name != null && !name.isEmpty();
    }

    public static boolean isValidSurname(String surname) {
        return surname != null && !surname.isEmpty();
    }
}
