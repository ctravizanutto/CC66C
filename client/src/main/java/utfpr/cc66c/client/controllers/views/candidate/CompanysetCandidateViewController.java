package utfpr.cc66c.client.controllers.views.candidate;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import utfpr.cc66c.client.controllers.connection.ClientConnectionController;
import utfpr.cc66c.client.controllers.connection.SessionController;
import utfpr.cc66c.client.services.ParseCompanyset;
import utfpr.cc66c.client.views.CompanyHboxFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CompanysetCandidateViewController implements Initializable {
    public VBox vbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var response = getCompany();
        updateList(response);
    }


    private String getCompany() {
        var json = JsonNodeFactory.instance.objectNode();
        json.put("operation", "GET_COMPANY");
        json.set("data", JsonNodeFactory.instance.objectNode());
        json.put("token", SessionController.getToken());

        var response = ClientConnectionController.requestResponse(json.toString());
        System.out.println(response);
        return response;
    }

    private void updateList(String json) {
        var companies = ParseCompanyset.parseCompanyset(json);
        int count = 0;
        for (var company : companies) {
            var hbox = CompanyHboxFactory.createCompanyHbox(company, count++);
            vbox.getChildren().add(hbox);
        }
    }
}
