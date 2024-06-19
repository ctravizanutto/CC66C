package utfpr.cc66c.server.services.jobs;

import utfpr.cc66c.server.services.db.DatabaseDriver;

import java.util.Map;

public class IncludeJob {
    public static void includeJob(String id, Map<String, String> fields) {
        var skill = fields.get("skill");
        var experience = fields.get("experience");
        var sql = String.format("INSERT INTO jobs (recruiter_id, skill, experience) VALUES ('%s', '%s', '%s')", id, skill, experience);
        DatabaseDriver.update(sql);
    }
}
