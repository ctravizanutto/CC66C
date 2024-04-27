package utfpr.cc66c.server.validators;

import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;

import java.util.Objects;

public class AuthValidator {
    public static boolean assertLogin(ObjectNode json) {
        var fields = JsonFields.getStringFields(json);
        var operation = fields.get("operation");
        var email = fields.get("email");
        var password = fields.get("password");

        if (operation == null || email == null || password == null) {
            return false;
        } else return !operation.isBlank() && !email.isBlank() && !password.isBlank();
    }

    public static boolean assertSignUp(ObjectNode json) {
        if (assertLogin(json)) {
            var fields = JsonFields.getStringFields(json);
            var operation = fields.get("operation");

            if (Objects.equals(operation, "SIGNUP_CANDIDATE")) {
                var industry = fields.get("industry");
                var description = fields.get("description");
                if (industry == null || description == null) {
                    return false;
                } else return !industry.isBlank() && !description.isBlank();
            }
            return true;
        }
        return false;
    }
}
