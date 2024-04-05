package utfpr.cc66c.client.controllers.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utfpr.cc66c.client.services.AuthHandler;
import utfpr.cc66c.core.models.LoginModel;
import utfpr.cc66c.core.types.UserType;
import utfpr.cc66c.core.validators.LoginValidator;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ChoiceBox<String> choiceBoxLogin;
    private final String[] userTypes = {"Candidate", "Recruiter"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxLogin.getItems().addAll(userTypes);
        choiceBoxLogin.setValue(userTypes[0]);
    }

    @FXML
    public void onEnterLogin() {
        var loginModel = validLoginFields();
        if (loginModel != null)
            AuthHandler.sendLoginRequest(loginModel);
    }

    public LoginModel validLoginFields() {
        var emailAddr = emailField.getText();
        var password = passwordField.getText();
        var userType = getChoiceToUserType();

        if (!LoginValidator.emailIsValid(emailAddr)) {
            emailField.getStyleClass().add("error");
            return null;
        } else if (emailField.getStyleClass().contains("error")) {
            emailField.getStyleClass().removeAll("error");
            emailField.setStyle(null);
        }
        if (!LoginValidator.passwordIsValid(password)) {
            passwordField.getStyleClass().add("error");
            return null;
        } else if (passwordField.getStyleClass().contains("error")) {
            passwordField.getStyleClass().removeAll("error");
            passwordField.setStyle(null);
        }

        return new LoginModel(emailAddr, password, getChoiceToUserType());
    }

    public UserType getChoiceToUserType() {
        var userType = choiceBoxLogin.getValue();
        return switch (userType) {
            case "Candidate" -> UserType.CANDIDATE;
            case "Recruiter" -> UserType.RECRUITER;
            default -> throw new RuntimeException();
        };
    }

}