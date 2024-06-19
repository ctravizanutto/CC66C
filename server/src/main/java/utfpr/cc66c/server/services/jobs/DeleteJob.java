package utfpr.cc66c.server.services.jobs;

import utfpr.cc66c.server.services.db.DatabaseDriver;

public class DeleteJob {
    public static void deleteJob(String recruiter_id, String job_id) {
        var sql = String.format("DELETE FROM jobs WHERE recruiter_id = '%s' AND job_id = '%s'", recruiter_id, job_id);
        DatabaseDriver.update(sql);
    }
}
