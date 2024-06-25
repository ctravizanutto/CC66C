package utfpr.cc66c.client.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            var available = Objects.equals(jobNode.get("available").asText(), "YES");
            var searchable = Objects.equals(jobNode.get("searchable").asText(), "YES");

            jobs.add(new Job(jobName, experience, id, available, searchable));
        }

        // Return the list of parsed jobs as an iterable
        return jobs;
    }

    public static class Job {
        private final String name;
        private final String experience;
        private final String id;
        private final boolean searchable;
        private final boolean available;

        public Job(String name, String experience, String id, boolean available, boolean searchable) {
            this.name = name;
            this.experience = experience;
            this.id = id;
            this.available = available;
            this.searchable = searchable;
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

        public boolean getAvailable() {
            return available;
        }

        public boolean getSearchable() {
            return searchable;
        }
    }
}
