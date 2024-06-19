package utfpr.cc66c.server.services.skill;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import utfpr.cc66c.server.services.db.DatabaseDriver;

import java.sql.SQLException;

public class LookupSkillset {
    public static ArrayNode lookupSkillset(String id) {
        var skillset = JsonNodeFactory.instance.arrayNode();
        var sql = String.format("SELECT skill, experience FROM skillsets WHERE candidate_id = '%s'", id);

        var resultSet = DatabaseDriver.query(sql);

        assert resultSet != null;
        try {
            while (resultSet.next()) {
                var skill = JsonNodeFactory.instance.objectNode();
                skill.put("skill", resultSet.getString("skill"));
                skill.put("experience", resultSet.getString("experience"));
                skillset.add(skill);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return skillset;
    }

}
