package utfpr.cc66c.server.services.skill;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.server.services.db.DatabaseDriver;

import java.sql.SQLException;

public class LookupSkillset {
    public static ObjectNode lookupSkillset(String id) {
        var skillset = JsonNodeFactory.instance.objectNode();
        var sql = String.format("SELECT skill, experience FROM skillsets WHERE candidate_id = '%s'", id);

        var resultSet = DatabaseDriver.query(sql);

        assert resultSet != null;
        try {
            while (resultSet.next()) {
                skillset.put("skill", resultSet.getString("skill"));
                skillset.put("experience", resultSet.getInt("experience"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return skillset;
    }

}
