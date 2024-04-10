package utfpr.cc66c.server.controllers.auth;

import utfpr.cc66c.core.validators.FieldValidator;
import utfpr.cc66c.server.services.db.DatabaseDriver;
import utfpr.cc66c.server.services.db.DatabaseService;

import java.sql.SQLException;
import java.util.Objects;


public class LoginController extends DatabaseService {
    public static String getCandidatePasswordByEmail(String email) {
        var sql = "SELECT password FROM candidates WHERE email = \"" + email + "\"";
        var query = DatabaseDriver.query(sql);
        try {
            assert query != null;
            return query.getString("password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getRecruiterPasswordByEmail(String email) {
        var sql = "SELECT password FROM recruiters WHERE email = \"" + email + "\"";
        var query = DatabaseDriver.query(sql);
        try {
            assert query != null;
            return query.getString("password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getLoginStatus(String operation, String email, String password) {
        if (FieldValidator.invalidEmail(email)) {
            return "INVALID_EMAIL";
        } else if (FieldValidator.invalidPassword(password)) {
            return "INVALID_PASSWORD";
        }

        String dbPassword;
        if (operation.contains("CANDIDATE")) {
            dbPassword = LoginController.getCandidatePasswordByEmail(email);
        } else {
            dbPassword = LoginController.getRecruiterPasswordByEmail(email);
        }

        if (dbPassword == null) {
            return "USER_NOT_FOUND";
        } else if (!Objects.equals(dbPassword, password)) {
            return "INVALID_PASSWORD";
        } else {
            return "SUCCESS";
        }
    }

}
