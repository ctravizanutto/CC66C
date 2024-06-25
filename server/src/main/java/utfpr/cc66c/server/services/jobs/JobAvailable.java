package utfpr.cc66c.server.services.jobs;

import utfpr.cc66c.server.services.db.DatabaseDriver;

public class JobAvailable {
    public static void setJobAvailable(String job_id, String recruiter_id, String available) {
        var sql =
                String.format("UPDATE jobs SET available = '%s' WHERE job_id = '%s' AND recruiter_id = '%s'", available, job_id, recruiter_id);
        DatabaseDriver.update(sql);
    }
}
