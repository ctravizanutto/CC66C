package utfpr.cc66c.server.services.skill;

import utfpr.cc66c.server.services.db.DatabaseDriver;

public class UpdateSkill {
    public static void updateSkill(String id, String oldSkill, String newSkill) {
        var sql = String.format("UPDATE skillsets SET skill='%s' WHERE candidate_id='%s' AND skill='%s'", newSkill, id, oldSkill);
        DatabaseDriver.update(sql);
    }
}
