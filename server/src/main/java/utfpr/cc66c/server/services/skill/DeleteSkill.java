package utfpr.cc66c.server.services.skill;

import utfpr.cc66c.server.services.db.DatabaseDriver;

public class DeleteSkill {
    public static boolean deleteSkill(String id, String skill) {
        var sql = String.format("DELETE FROM skillsets WHERE candidate_id = '%s' AND skill = '%s'", id, skill);
        return DatabaseDriver.update(sql);

    }
}
