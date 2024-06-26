package utfpr.cc66c.client.controllers.views.recruiter;

import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import utfpr.cc66c.client.controllers.views.ApplicationViewController;
import utfpr.cc66c.client.services.JobsetConnectionHandler;
import utfpr.cc66c.client.services.ParseJobset;
import utfpr.cc66c.client.views.RecruiterJobHboxFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class JobsetRecruiterViewController implements Initializable {
    public VBox vbox;

    public void onAddButtonAction() {
        ApplicationViewController.toRecruiterAddJob();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateList();
    }

    public void updateList() {
        vbox.getChildren().subList(1, vbox.getChildren().size()).clear();
        var response = JobsetConnectionHandler.sendJobsetLookup();
        var jobsetSize = JobsetConnectionHandler.parseJobsetLookup(response);
        if (jobsetSize > 0) {
            generateJobsetList(jobsetSize, response);
        }
    }

    public void generateJobsetList(int jobsetSize, String response) {
        var jobset = ParseJobset.parseJobsetRecruiter(response);
        for (var job : jobset) {
            var hbox = RecruiterJobHboxFactory.createRecruiterJobHbox(job.id(), job.skill(), job.experience(), jobsetSize--, job.available(), job.searchable());
            vbox.getChildren().add(hbox);
        }
    }
}
