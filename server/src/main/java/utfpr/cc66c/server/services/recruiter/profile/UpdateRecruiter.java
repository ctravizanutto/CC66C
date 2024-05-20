package utfpr.cc66c.server.services.recruiter.profile;

import utfpr.cc66c.server.services.db.DatabaseDriver;

import java.util.Map;

public class UpdateRecruiter {
    public static void update(String id, Map<String, String> fields) {
        var name = fields.get("name");
        var email = fields.get("email");
        var password = fields.get("password");
        var description = fields.get("description");
        var industry = fields.get("industry");

        var sql = "UPDATE recruiters SET name =\"" + name + "\",email=\"" + email + "\",password=\"" + password + "\",industry=\"" + industry + "\",description=\"" + description + "\" WHERE recruiter_id =" + id;
        DatabaseDriver.update(sql);
    }
}
