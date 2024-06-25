package utfpr.cc66c.server.services.jobs;

import utfpr.cc66c.server.services.db.DatabaseDriver;

import java.sql.SQLException;

public class LookupJob {
    public static String[] lookupJob(String recruiter_id, String job_id) {
        String[] job;
        var sql = String.format("SELECT skill, experience, available, searchable FROM jobs WHERE recruiter_id = '%s' AND job_id = '%s'", recruiter_id, job_id);
        var resultSet = DatabaseDriver.query(sql);
        try {
            if (resultSet == null) {
                return null;
            }
            job = new String[4];
            job[0] = resultSet.getString("skill");
            job[1] = resultSet.getString("experience");
            job[2] = resultSet.getString("available");
            job[3] = resultSet.getString("searchable");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return job;
    }
}
