package utfpr.cc66c.client.services;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.client.controllers.connection.ClientConnectionController;
import utfpr.cc66c.client.controllers.connection.SessionController;
import utfpr.cc66c.client.controllers.views.ApplicationViewController;

public class LogoutHandler {
    public static void sendLogoutRequest() {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        json.set("data", JsonNodeFactory.instance.objectNode());
        json.put("operation", "LOGOUT_CANDIDATE");
        json.put("token", SessionController.getToken());

        var response = ClientConnectionController.requestResponse(json.toString());
        System.out.printf("[INFO] Logout response: %s\n", response);
        ApplicationViewController.logout();
    }
}
