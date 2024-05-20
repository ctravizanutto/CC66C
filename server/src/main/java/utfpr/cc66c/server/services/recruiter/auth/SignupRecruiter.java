package utfpr.cc66c.server.services.recruiter.auth;

import utfpr.cc66c.server.services.db.DatabaseDriver;

public class SignupRecruiter {
    public static void signup(String email, String password, String name, String description, String industry) {
        var sql = String.format("INSERT INTO recruiters (email, password, name, description, industry) VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\")", email, password, name, description, industry);
        DatabaseDriver.update(sql);
    }
}
