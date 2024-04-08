package utfpr.cc66c.core.validators;

import java.util.regex.Pattern;

public class FieldValidator {
    public static boolean invalidEmail(String emailAddr) {
        if (emailAddr.isBlank())
            return true;

        var regexPattern = "^(?=.{1,64}@)[A-Za-z0-9+_-]+(\\.[A-Za-z0-9+_-]+)*@"
                + "[^-][A-Za-z0-9+-]+(\\.[A-Za-z0-9+-]+)*(\\.[A-Za-z]{2,})$";

        return !Pattern.compile(regexPattern)
                .matcher(emailAddr)
                .matches();
    }

    public static boolean invalidPassword(String password) {
        return password.isBlank() || password.length() > 255;
    }
}
