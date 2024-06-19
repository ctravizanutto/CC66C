package utfpr.cc66c.client.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class ParseJobset {

    public static Iterable<Job> parseJobset(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        try {
            node = mapper.readTree(jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Extract the jobset array
        JsonNode jobsetNode = node.get("data").get("jobset");

        // Create an empty list to store parsed jobs
        List<Job> jobs = new ArrayList<>();

        // Iterate through the jobset array and parse each job object
        for (JsonNode jobNode : jobsetNode) {
            String jobName = jobNode.get("skill").asText();
            var experience = jobNode.get("experience").asText();
            var id = jobNode.get("id").asText();

            jobs.add(new Job(jobName, experience, id));
        }

        // Return the list of parsed jobs as an iterable
        return jobs;
    }

    public static class Job {
        private final String name;
        private final String experience;
        private final String id;

        public Job(String name, String experience, String id) {
            this.name = name;
            this.experience = experience;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public String getExperience() {
            return experience;
        }

        public String getId() {
            return id;
        }
    }
}
