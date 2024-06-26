package utfpr.cc66c.client.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class ParseSkillset {

    public static Iterable<Skill> parseSkillset(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        try {
            node = mapper.readTree(jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Extract the skillset array
        JsonNode skillsetNode = node.get("data").get("skillset");

        // Create an empty list to store parsed skills
        List<Skill> skills = new ArrayList<>();

        // Iterate through the skillset array and parse each skill object
        for (JsonNode skillNode : skillsetNode) {
            String skillName = skillNode.get("skill").asText();
            var experience = skillNode.get("experience").asText();

            skills.add(new Skill(skillName, experience));
        }

        // Return the list of parsed skills as an iterable
        return skills;
    }

    public static class Skill {
        private final String name;
        private final String experience;

        public Skill(String name, String experience) {
            this.name = name;
            this.experience = experience;
        }

        public String getName() {
            return name;
        }

        public String getExperience() {
            return experience;
        }
    }
}
