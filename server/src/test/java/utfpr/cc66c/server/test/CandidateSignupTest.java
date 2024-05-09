package utfpr.cc66c.server.test;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.junit.jupiter.api.Test;
import utfpr.cc66c.server.services.RequestParser;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CandidateSignupTest {

    @Test
    void invalidOperation() {
        var json = JsonNodeFactory.instance.objectNode();
        json.put("operation", "INVALID");

        var response = RequestParser.parseJson(json.toString());
        assertTrue(response.contains("INVALID_OPERATION"));
    }

    @Test
    void nullOperation() {
        var response = RequestParser.parseJson("{}");
        assertTrue(response.contains("INVALID_OPERATION"));
    }

    @Test
    void invalidField() {
        var json = JsonNodeFactory.instance.objectNode();
        json.put("operation", "SIGNUP_CANDIDATE");

        var data = JsonNodeFactory.instance.objectNode();
        json.set("data", data);

        var response = RequestParser.parseJson(json.toString());
        assertTrue(response.contains("INVALID_FIELD"));
    }

}


