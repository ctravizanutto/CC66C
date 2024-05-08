package utfpr.cc66c.server.services.candidate.profile;

import utfpr.cc66c.server.services.db.DatabaseDriver;

import java.util.Map;

public class UpdateCandidate {
    public static void update(String id, Map<String, String> fields) {
        var name = fields.get("name");
        var email = fields.get("email");
        var password = fields.get("password");

        var sql = "UPDATE candidates SET name =\"" + name + "\",email=\"" + email + "\",password=\"" + password + "\" WHERE candidate_id =" + id;
        DatabaseDriver.update(sql);
    }
}
