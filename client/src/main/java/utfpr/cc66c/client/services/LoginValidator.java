package utfpr.cc66c.client.services;

import utfpr.cc66c.client.controllers.views.LoginViewController;
import utfpr.cc66c.client.views.LoginViewFactory;
import utfpr.cc66c.core.models.LoginModel;
import utfpr.cc66c.core.validators.FieldValidator;

public class LoginValidator {
    public static LoginModel validLoginFields() {
        var emailField = LoginViewController.getInstance().emailLoginField;
        var passwordField = LoginViewController.getInstance().passwordLoginField;
        var emailAddr = emailField.getText();
        var password = passwordField.getText();

        if (FieldValidator.invalidEmail(emailAddr)) {
            LoginViewFactory.setErrorField(emailField);
            return null;
        }
        if (FieldValidator.invalidPassword(password)) {
            LoginViewFactory.setErrorField(passwordField);
            return null;
        }
        LoginViewFactory.clearErrorFields(emailField, passwordField);

        return new LoginModel(emailAddr, password, LoginViewController.getInstance().toggleSwitchToUserType());
    }
}
