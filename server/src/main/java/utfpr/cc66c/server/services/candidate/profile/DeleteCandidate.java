package utfpr.cc66c.server.services.candidate.profile;

import utfpr.cc66c.server.services.db.DatabaseDriver;

public class DeleteCandidate {
    public static void deleteCandidate(String id) {
        var sql = "DELETE FROM candidates WHERE candidate_id = '" + id + "'";
        DatabaseDriver.update(sql);
    }
}
