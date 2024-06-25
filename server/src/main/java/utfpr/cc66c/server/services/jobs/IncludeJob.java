package utfpr.cc66c.server.services.jobs;

import utfpr.cc66c.server.services.db.DatabaseDriver;

import java.util.Map;

public class IncludeJob {
    public static void includeJob(String id, Map<String, String> fields) {
        var skill = fields.get("skill");
        var experience = fields.get("experience");
        var available = fields.get("available");
        var searchable = fields.get("searchable");
        var sql =
                String.format("INSERT INTO jobs (recruiter_id, skill, experience, available, searchable) VALUES ('%s', '%s', '%s', '%s', '%s')", id, skill, experience, available, searchable);
        DatabaseDriver.update(sql);
    }
}
