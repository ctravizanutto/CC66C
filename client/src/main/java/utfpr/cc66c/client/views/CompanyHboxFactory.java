package utfpr.cc66c.client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class CompanyHboxFactory {
    public static HBox createCompanyHbox(String name, int order) {
        var loader = new FXMLLoader(CompanyHboxFactory.class.getResource("/utfpr/cc66c/client/dashboard/candidate/hbox-companyset.fxml"));
        HBox hbox;
        try {
            hbox = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var color = (order % 2 == 0) ? "#76adea" : "#7076fc";
        ((Text) hbox.getChildren().getFirst()).setText(name);
        hbox.setStyle("-fx-background-color: " + color + ";");

        return hbox;
    }
}
