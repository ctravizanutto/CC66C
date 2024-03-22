package utfpr.cc66c.client.controllers.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utfpr.cc66c.client.controllers.LoginController;
import utfpr.cc66c.client.types.UserType;

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
    public void onEnterLogin(ActionEvent ignoredE) {
        LoginController.validateLoginFields(emailField, passwordField);
    }

    public UserType getOption() {
        var userType = choiceBoxLogin.getValue();
        return switch (userType) {
            case "Candidate" -> UserType.CANDIDATE;
            case "Recruiter" -> UserType.RECRUITER;
            default -> throw new RuntimeException();
        };
    }

}