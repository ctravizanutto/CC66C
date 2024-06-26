package utfpr.cc66c.client.controllers.views.candidate;

import javafx.scene.layout.VBox;
import utfpr.cc66c.client.services.JobsetConnectionHandler;
import utfpr.cc66c.client.services.ParseJobset;
import utfpr.cc66c.client.views.CandidateSkillHboxFactory;

public class JobsetCandidateViewController {
    public VBox vbox;

    public void updateList(boolean andFilter) {
        vbox.getChildren().clear();
        var response = JobsetConnectionHandler.sendJobSearch(andFilter);
        var jobsetSize = JobsetConnectionHandler.parseJobsetLookup(response);
        if (jobsetSize > 0) {
            generateJobList(jobsetSize, response);
        }
    }

    private void generateJobList(int jobsetSize, String response) {
        var jobset = ParseJobset.parseJobsetCandidate(response);
        for (var job : jobset) {
            var hbox = CandidateSkillHboxFactory.createCandidateSkillHbox(job.name(), job.experience(), jobsetSize--);
            vbox.getChildren().add(hbox);
        }
    }

}
