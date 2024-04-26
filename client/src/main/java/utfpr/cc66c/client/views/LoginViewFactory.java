package utfpr.cc66c.client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import org.controlsfx.control.ToggleSwitch;
import utfpr.cc66c.client.controllers.views.LoginViewController;

import java.io.IOException;

public class LoginViewFactory {
    private static LoginViewFactory instance;
    private Scene loginScene;

    public static LoginViewFactory getInstance() {
        if (instance == null) {
            instance = new LoginViewFactory();
        }
        return instance;
    }

    private void setLoginScene() {
        var fxmlLoader = new FXMLLoader(getClass().getResource("/utfpr/cc66c/client/login/login-view.fxml"));
        try {
            fxmlLoader.setController(LoginViewController.getInstance());
            loginScene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Scene getScene() {
        if (loginScene == null) {
            setLoginScene();
        }
        return loginScene;
    }

    public static void setErrorField(Control field) {
        field.getStyleClass().add("error");
    }

    public static void clearErrorFields(Control... fields) {
        for (var field : fields) {
            if (field.getStyleClass().contains("error")) {
                field.getStyleClass().removeAll("error");
                field.setStyle(null);
            }
        }
    }

    public static void paneFromTo(Pane from, Pane to) {
        from.setDisable(true);
        from.setVisible(false);

        to.setDisable(false);
        to.setVisible(true);
    }

    public static void toggleSwitch(ToggleSwitch toggleSwitch, Control... fields) {
        var industryField = fields[0];
        var descriptionField = fields[1];

        if (toggleSwitch.isSelected()) {
            industryField.setDisable(false);
            descriptionField.setDisable(false);
        } else {
            industryField.setDisable(true);
            descriptionField.setDisable(true);
        }
    }
}
