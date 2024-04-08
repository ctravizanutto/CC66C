package utfpr.cc66c.client.controllers.views;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.ToggleSwitch;
import utfpr.cc66c.client.services.AuthRequestHandler;
import utfpr.cc66c.client.services.LoginValidator;
import utfpr.cc66c.client.services.SignupValidator;
import utfpr.cc66c.client.views.LoginViewFactory;
import utfpr.cc66c.core.types.UserType;

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
    public TextField emailLoginField;
    @FXML
    public PasswordField passwordLoginField;
    @FXML
    public ToggleSwitch toggleSwitch;

    @FXML
    public void onEnterLogin() {
        var loginModel = LoginValidator.validLoginFields();
        if (loginModel != null)
            AuthRequestHandler.sendLoginRequest(loginModel);
    }

    public UserType toggleSwitchToUserType() {
        return toggleSwitch.isSelected() ? UserType.CANDIDATE : UserType.RECRUITER;
    }

    public void toSignup() {
        LoginViewFactory.paneFromTo(loginPane, signupPane);
    }

    public void toLogin() {
        LoginViewFactory.paneFromTo(signupPane, loginPane);
    }

    public void onEnterSignup() {
        var signupModel = SignupValidator.validSignupFields();
        if (signupModel != null)
            AuthRequestHandler.sendSignupRequest(signupModel);
    }

    public void toggleSwitch() {
        LoginViewFactory.toggleSwitch(toggleSwitch, industryField, descriptionField);
    }
}