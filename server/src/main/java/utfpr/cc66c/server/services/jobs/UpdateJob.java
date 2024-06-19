package utfpr.cc66c.server.services.jobs;

import utfpr.cc66c.server.services.db.DatabaseDriver;

public class UpdateJob {
    public static void updateJob(String recruiter_id, String job_id, String skill, String experience) {
        var sql = String.format("UPDATE jobs SET skill='%s', experience='%s' WHERE recruiter_id='%s' AND job_id='%s'", skill, experience, recruiter_id, job_id);
        DatabaseDriver.update(sql);
    }
}
