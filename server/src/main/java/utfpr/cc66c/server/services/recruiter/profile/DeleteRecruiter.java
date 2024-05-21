package utfpr.cc66c.server.services.recruiter.profile;

import utfpr.cc66c.server.services.db.DatabaseDriver;

public class DeleteRecruiter {
    public static void deleteRecruiter(String id) {
        var sql = "DELETE FROM recruiters WHERE recruiter_id = '" + id + "'";
        DatabaseDriver.update(sql);
    }
}
