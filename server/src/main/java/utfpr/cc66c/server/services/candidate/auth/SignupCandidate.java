package utfpr.cc66c.server.services.candidate.auth;

import utfpr.cc66c.server.services.db.DatabaseDriver;

public class SignupCandidate {
    public static void signup(String email, String password, String name) {
        var sql = String.format("INSERT INTO candidates (email, password, name) VALUES (\"%s\", \"%s\", \"%s\")", email, password, name);
        DatabaseDriver.update(sql);
    }
}
