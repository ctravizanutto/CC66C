package utfpr.cc66c.server.services.candidate;

import utfpr.cc66c.server.services.db.DatabaseDriver;

public class ChooseCandidate {
    public static void chooseCandidate(String candidate_id, String recruiter_id) {
        var sql = String.format("INSERT INTO candidates_recruiters VALUES ('%s', '%s')", candidate_id, recruiter_id);
        DatabaseDriver.update(sql);
    }
}
