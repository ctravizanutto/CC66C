package utfpr.cc66c.server.test;

import org.junit.jupiter.api.Test;
import utfpr.cc66c.server.services.RequestParser;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchJobTest {
    @Test
    void test() {
        var json = "{\"operation\":\"SEARCH_JOB\",\"token\":\"\",\"data\":{\"skill\":[\"C\",\"JAVA\"],\"experience\":\"0\",\"filter\":\"AND\"}}";

        var response = RequestParser.parseJson(json);
        assertTrue(response.contains("INVALID_OPERATION"));
    }
}
