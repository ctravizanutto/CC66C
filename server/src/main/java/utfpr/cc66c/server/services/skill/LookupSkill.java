package utfpr.cc66c.server.services.skill;

import utfpr.cc66c.server.services.db.DatabaseDriver;

import java.sql.SQLException;

public class LookupSkill {
    public static String[] lookupSkill(String id, String skill) {
        String[] skillArray = null;
        var sql = String.format("SELECT skill, experience FROM skillsets WHERE candidate_id = '%s' AND skill = '%s'", id, skill);
        var resultSet = DatabaseDriver.query(sql);
        try {
            assert resultSet != null;
            if (resultSet.next()) {
                skillArray = new String[2];
                skillArray[0] = resultSet.getString("skill");
                skillArray[1] = resultSet.getString("experience");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return skillArray;
    }
}
