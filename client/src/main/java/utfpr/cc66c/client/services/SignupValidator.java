package utfpr.cc66c.client.services;

import utfpr.cc66c.client.controllers.views.ApplicationViewController;
import utfpr.cc66c.client.views.LoginViewFactory;
import utfpr.cc66c.core.models.CandidateModel;
import utfpr.cc66c.core.models.LoginModel;
import utfpr.cc66c.core.models.RecruiterModel;
import utfpr.cc66c.core.models.SignupModel;
import utfpr.cc66c.core.types.UserType;
import utfpr.cc66c.core.validators.FieldValidator;

public class SignupValidator {
    public static SignupModel validSignupFields() {
        var login = validLoginFields();
        if (login == null) return null;

        var nameField = ApplicationViewController.loginController.nameField;
        var name = nameField.getText();
        if (name == null || name.isBlank()) {
            LoginViewFactory.setErrorField(nameField);
            return null;
        }
        LoginViewFactory.clearErrorFields(nameField);

        if (ApplicationViewController.loginController.toggleSwitchToUserType().equals(UserType.CANDIDATE)) {
            return new CandidateModel(name, login);
        }
        return validRecruiterFields(name, login);
    }

    private static LoginModel validLoginFields() {
        var emailField = ApplicationViewController.loginController.emailSignupField;
        var passwordField = ApplicationViewController.loginController.passwordSignupField;
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

    private static RecruiterModel validRecruiterFields(String name, LoginModel login) {
        var industryField = ApplicationViewController.loginController.industryField;
        var descriptionField = ApplicationViewController.loginController.descriptionField;
        var industry = industryField.getText();
        var description = descriptionField.getText();

        if (industry == null || industry.isBlank()) {
            LoginViewFactory.setErrorField(industryField);
            return null;
        }
        if (description == null || description.isBlank()) {
            LoginViewFactory.setErrorField(descriptionField);
            return null;
        }
        LoginViewFactory.clearErrorFields(industryField, descriptionField);

        return new RecruiterModel(name, description, industry, login);
    }
}
