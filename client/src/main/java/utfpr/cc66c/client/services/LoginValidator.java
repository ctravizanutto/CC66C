package utfpr.cc66c.client.services;

import utfpr.cc66c.client.controllers.views.ApplicationViewController;
import utfpr.cc66c.client.views.LoginViewFactory;
import utfpr.cc66c.core.models.LoginModel;
import utfpr.cc66c.core.validators.FieldValidator;

public class LoginValidator {
    public static LoginModel validLoginFields() {
        var emailField = ApplicationViewController.loginController.emailLoginField;
        var passwordField = ApplicationViewController.loginController.passwordLoginField;
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

        return new LoginModel(emailAddr, password, ApplicationViewController.loginController.toggleSwitchToUserType());
    }
}
