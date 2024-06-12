package utfpr.cc66c.server.services.skill;

import utfpr.cc66c.server.services.db.DatabaseDriver;

import java.util.Map;

public class IncludeSkill {
    public static void includeSkill(String id, Map<String, String> fields) {
        var skill = fields.get("skill");
        var experience = fields.get("experience");
        var sql = String.format("INSERT INTO skillsets (candidate_id, skill, experience) VALUES ('%s', '%s', '%s')", id, skill, experience);
        DatabaseDriver.update(sql);
    }
}
