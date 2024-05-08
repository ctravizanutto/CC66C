package utfpr.cc66c.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.server.controllers.candidate.auth.LoginCandidateController;
import utfpr.cc66c.server.controllers.candidate.auth.SignupCandidateController;
import utfpr.cc66c.server.controllers.candidate.profile.DeleteCandidateController;
import utfpr.cc66c.server.controllers.candidate.profile.LookupCandidateController;
import utfpr.cc66c.server.controllers.candidate.profile.UpdateCandidateController;

public class RequestParser {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String parseJson(String request) {
        ObjectNode json;
        try {
            json = (ObjectNode) mapper.readTree(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        var operation = json.get("operation").asText();
        switch (operation) {
            // Auth
            case "LOGIN_CANDIDATE" -> {
                return LoginCandidateController.loginCandidate(json);
            }
            case "LOGOUT_CANDIDATE" -> {
                return LoginCandidateController.logoutCandidate(json);
            }
            case "SIGNUP_CANDIDATE" -> {
                return SignupCandidateController.signupCandidate(json);
            }
            // Lookup
            case "LOOKUP_ACCOUNT_CANDIDATE" -> {
                return LookupCandidateController.lookupCandidate(json);
            }
            // Delete
            case "DELETE_ACCOUNT_CANDIDATE" -> {
                return DeleteCandidateController.deleteCandidate(json);
            }
            // Update
            case "UPDATE_ACCOUNT_CANDIDATE" -> {
                return UpdateCandidateController.updateCandidate(json);
            }
            default -> {
                System.out.println("[ERROR] Invalid operation: " + operation);
                json.put("status", "INVALID_OPERATION");
                json.set("data", JsonNodeFactory.instance.objectNode());
                json.remove("token");

                return json.toString();
            }
        }
    }

}
