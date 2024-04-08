package utfpr.cc66c.client.controllers.views;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.ToggleSwitch;
import utfpr.cc66c.client.services.AuthHandler;
import utfpr.cc66c.core.models.LoginModel;
import utfpr.cc66c.core.types.UserType;
import utfpr.cc66c.core.validators.LoginValidator;

public class LoginViewController {
    @FXML
    public AnchorPane loginPane;
    @FXML
    public AnchorPane signupPane;
    @FXML
    public TextField emailSignupField;
    @FXML
    public PasswordField passwordSignupField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField industryField;
    @FXML
    public TextArea descriptionField;
    @FXML
    private TextField emailLoginField;
    @FXML
    private PasswordField passwordLoginField;
    @FXML
    private ToggleSwitch toggleSwitch;

    @FXML
    public void onEnterLogin() {
        var loginModel = validLoginFields();
        if (loginModel != null)
            AuthHandler.sendLoginRequest(loginModel);
    }

    public LoginModel validLoginFields() {
        var emailAddr = emailLoginField.getText();
        var password = passwordLoginField.getText();

        if (LoginValidator.invalidEmail(emailAddr)) {
            emailLoginField.getStyleClass().add("error");
            return null;
        } else if (emailLoginField.getStyleClass().contains("error")) {
            emailLoginField.getStyleClass().removeAll("error");
            emailLoginField.setStyle(null);
        }
        if (LoginValidator.invalidPassword(password)) {
            passwordLoginField.getStyleClass().add("error");
            return null;
        } else if (passwordLoginField.getStyleClass().contains("error")) {
            passwordLoginField.getStyleClass().removeAll("error");
            passwordLoginField.setStyle(null);
        }

        return new LoginModel(emailAddr, password, toggleSwitchToUserType());
    }

    public UserType toggleSwitchToUserType() {
        return toggleSwitch.isSelected() ? UserType.CANDIDATE : UserType.RECRUITER;
    }

    public void toSignup() {
        loginPane.setDisable(true);
        loginPane.setVisible(false);
        signupPane.setDisable(false);
        signupPane.setVisible(true);
    }

    public void toLogin() {
        loginPane.setDisable(false);
        loginPane.setVisible(true);
        signupPane.setDisable(true);
        signupPane.setVisible(false);
    }

    public void onEnterSignup() {
    }

    public void toggleSwitch() {
        if (toggleSwitch.isSelected()) {
            industryField.setDisable(false);
            descriptionField.setDisable(false);
        } else {
            industryField.setDisable(true);
            descriptionField.setDisable(true);
        }
    }
}