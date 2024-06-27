package utfpr.cc66c.client.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class ParseCompanyset {

    public static Iterable<String> parseCompanyset(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        try {
            node = mapper.readTree(jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Extract the jobset array
        JsonNode companyNode = node.get("data").get("company");

        // Create an empty list to store parsed jobs
        List<String> companies = new ArrayList<>();

        // Iterate through the jobset array and parse each job object
        for (var company : companyNode) {
            String companyName = company.get("name").asText();
            companies.add(companyName);
        }

        // Return the list of parsed jobs as an iterable
        return companies;
    }
}
