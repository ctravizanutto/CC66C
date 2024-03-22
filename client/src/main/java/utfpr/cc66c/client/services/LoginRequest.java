package utfpr.cc66c.client.services;

import org.json.JSONObject;
import utfpr.cc66c.client.ClientApplication;
import utfpr.cc66c.client.controllers.ClientController;

import java.util.Objects;

public class LoginRequest {
    public static void sendLoginRequest(String emailAddr, String password) {
        sendLoginJSON(emailAddr, password);
    }

    private static void sendLoginJSON(String emailAddr, String password) {
        var json = new JSONObject();
        var data = new JSONObject();

        data.put("email", emailAddr);
        data.put("password", password);

        var option = ClientApplication.getClientApplicationView().choiceBoxLogin.getValue();

        json.put("operation", Objects.equals(option, "Candidate") ? "LOGIN_CANDIDATE" : "LOGIN_RECRUITER");
        json.put("data", data);

        var response = ClientController.sendJSON(json.toString());
        parseLoginResponse(response);
    }

    private static void parseLoginResponse(String response) {
        var json = new JSONObject(response);
        var status = json.get("status");
        if (Objects.equals(status, "SUCCESS")) {
            System.out.println("[INFO] Successful login.");
        }
    }
}
