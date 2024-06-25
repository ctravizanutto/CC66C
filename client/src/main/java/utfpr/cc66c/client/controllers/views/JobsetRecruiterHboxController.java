package utfpr.cc66c.client.controllers.views;

import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class JobsetRecruiterHboxController implements Initializable {
    public Text skillText;
    public Text expText;
    private String id;
    private boolean available;
    private boolean searchable;

    public void onMouseClicked() {
        ApplicationViewController.toRecruiterEditJob(skillText.getText(), expText.getText(), id, available, searchable);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        skillText.setText("NOT_LOADED");
        expText.setText("NOT_LOADED");
    }

    public void setText(String skillset, String experience, String id, boolean available, boolean searchable) {
        skillText.setText(skillset);
        expText.setText(experience);
        this.id = id;
        this.available = available;
        this.searchable = searchable;
    }
}
