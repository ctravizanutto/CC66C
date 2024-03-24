package utfpr.cc66c.client.controllers;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utfpr.cc66c.client.services.LoginRequest;

import java.util.regex.Pattern;

public class LoginController {

    public static void validateLoginFields(TextField emailField, PasswordField passwordField) {
        var emailAddr = emailField.getText();
        var password = passwordField.getText();
        var isValid = true;

        if (!emailIsValid(emailAddr)) {
            emailField.getStyleClass().add("error");
            isValid = false;
        } else {
            emailField.getStyleClass().removeAll("error");
            emailField.setStyle(null);
        }

        if (!passwordIsValid(password)) {
            passwordField.getStyleClass().add("error");
            isValid = false;
        } else {
            passwordField.getStyleClass().removeAll("error");
            passwordField.setStyle(null);
        }

        if (isValid)
            LoginRequest.sendLoginRequest(emailAddr, password);
    }

    private static boolean emailIsValid(String emailAddr) {
        if (emailAddr.isBlank())
            return false;

        var regexPattern = "^(?=.{1,64}@)[A-Za-z0-9+_-]+(\\.[A-Za-z0-9+_-]+)*@"
                + "[^-][A-Za-z0-9+-]+(\\.[A-Za-z0-9+-]+)*(\\.[A-Za-z]{2,})$";

        return Pattern.compile(regexPattern)
                .matcher(emailAddr)
                .matches();
    }

    private static boolean passwordIsValid(String password) {
        return !password.isBlank() && password.length() <= 255;
    }

}
