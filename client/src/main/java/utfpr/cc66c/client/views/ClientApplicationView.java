package utfpr.cc66c.client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utfpr.cc66c.client.controllers.LoginController;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientApplicationView implements Initializable {
    @FXML
    public TextField emailField;
    public PasswordField passwordField;
    public ChoiceBox<String> choiceBoxLogin;

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

}