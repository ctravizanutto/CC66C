package utfpr.cc66c.core.validators;

public class FieldValidator {
    public static boolean invalidEmail(String emailAddr) {
        return emailAddr.isBlank();
    }

    public static boolean invalidPassword(String password) {
        return password.isBlank();
    }

}
