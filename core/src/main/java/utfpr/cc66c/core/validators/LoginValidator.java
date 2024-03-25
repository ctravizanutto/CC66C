package utfpr.cc66c.core.validators;

import java.util.regex.Pattern;

public class LoginValidator {
    public static boolean emailIsValid(String emailAddr) {
        if (emailAddr.isBlank())
            return false;

        var regexPattern = "^(?=.{1,64}@)[A-Za-z0-9+_-]+(\\.[A-Za-z0-9+_-]+)*@"
                + "[^-][A-Za-z0-9+-]+(\\.[A-Za-z0-9+-]+)*(\\.[A-Za-z]{2,})$";

        return Pattern.compile(regexPattern)
                .matcher(emailAddr)
                .matches();
    }

    public static boolean passwordIsValid(String password) {
        return !password.isBlank() && password.length() <= 255;
    }
}
