package utfpr.cc66c.client.views;

import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import org.controlsfx.control.ToggleSwitch;

public class LoginViewFactory {
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
