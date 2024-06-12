package utfpr.cc66c.server.services.skill;

import utfpr.cc66c.server.services.db.DatabaseDriver;

import java.sql.SQLException;

public class LookupSkill {
    public static String lookupSkill(String id, String skill) {
        String experience;
        var sql = String.format("SELECT experience FROM skillsets WHERE candidate_id = '%s' AND skill = '%s'", id, skill);
        var resultSet = DatabaseDriver.query(sql);
        try {
            assert resultSet != null;

            experience = resultSet.getString("experience");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return experience;
    }
}
