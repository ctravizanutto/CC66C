package utfpr.cc66c.server.services.candidate;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.server.services.db.DatabaseDriver;

import java.sql.SQLException;

public class SearchCandidate {
    public static ObjectNode searchCandidate(String[] skillset, String experience, String filter) {
        var data = JsonNodeFactory.instance.objectNode();
        ArrayNode ret;
        if (experience == null || experience.isEmpty()) {
            ret = searchCandidateSkillset(skillset);
        } else if (skillset == null || skillset.length == 0) {
            ret = searchCandidateExperience(experience);
        } else {
            ret = searchCandidateSkillExperience(skillset, experience, filter);
        }
        data.set("profile", ret);
        data.put("profile_size", ret.size());

        return data;
    }

    private static ArrayNode searchCandidateSkillset(String[] skillset) {
        var candidate_set = JsonNodeFactory.instance.arrayNode();
        StringBuilder sql = new StringBuilder("SELECT * FROM skillsets WHERE ");
        for (var skill : skillset) {
            sql.append("skill=").append(String.format("'%s' OR ", skill));
        }
        sql.append("skill=").append("''");

        var rs = DatabaseDriver.query(sql.toString());
        assert rs != null;
        try {
            while (rs.next()) {
                var candidate = JsonNodeFactory.instance.objectNode();
                candidate.put("skill", rs.getString("skill"));
                candidate.put("experience", rs.getString("experience"));
                candidate.put("id_user", rs.getString("candidate_id"));
                candidate.put("id", rs.getString("skill_id"));
                candidate_set.add(candidate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return candidate_set;
    }

    private static ArrayNode searchCandidateExperience(String experience) {
        var candidate_set = JsonNodeFactory.instance.arrayNode();
        var sql = String.format("SELECT * FROM skillsets WHERE experience<='%s'", experience);
        var rs = DatabaseDriver.query(sql);
        assert rs != null;
        try {
            while (rs.next()) {
                var candidate = JsonNodeFactory.instance.objectNode();
                candidate.put("skill", rs.getString("skill"));
                candidate.put("experience", rs.getString("experience"));
                candidate.put("id_user", rs.getString("candidate_id"));
                candidate.put("id", rs.getString("skill_id"));
                candidate_set.add(candidate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return candidate_set;
    }

    private static ArrayNode searchCandidateSkillExperience(String[] skillset, String experience, String filter) {
        var candidate_set = JsonNodeFactory.instance.arrayNode();
        StringBuilder sql = new StringBuilder("SELECT * FROM skillsets WHERE ");
        for (var skill : skillset) {
            sql.append("skill=").append(String.format("'%s' OR ", skill));
        }
        sql.append("skill=").append("''");
        sql.append(String.format("%s experience <='%s'", filter, experience));

        var rs = DatabaseDriver.query(sql.toString());
        assert rs != null;
        try {
            while (rs.next()) {
                var candidate = JsonNodeFactory.instance.objectNode();
                candidate.put("skill", rs.getString("skill"));
                candidate.put("experience", rs.getString("experience"));
                candidate.put("id_user", rs.getString("candidate_id"));
                candidate.put("id", rs.getString("skill_id"));
                candidate_set.add(candidate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return candidate_set;
    }
}
