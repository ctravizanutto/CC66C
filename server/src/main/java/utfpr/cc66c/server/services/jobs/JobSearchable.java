package utfpr.cc66c.server.services.jobs;

import utfpr.cc66c.server.services.db.DatabaseDriver;

public class JobSearchable {
    public static void setJobSearchable(String job_id, String recruiter_id, String searchable) {
        var sql =
                String.format("UPDATE jobs SET searchable = '%s' WHERE job_id = '%s' AND recruiter_id = '%s'", searchable, job_id, recruiter_id);
        DatabaseDriver.update(sql);
    }
}
