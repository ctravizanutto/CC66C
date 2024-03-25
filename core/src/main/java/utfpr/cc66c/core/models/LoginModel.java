package utfpr.cc66c.core.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.LoginModelDeserializer;
import utfpr.cc66c.core.serializers.LoginModelSerializer;
import utfpr.cc66c.core.types.UserType;

@JsonSerialize(using = LoginModelSerializer.class)
@JsonDeserialize(using = LoginModelDeserializer.class)
public class LoginModel {
    public final String login;
    public final String password;
    public final UserType userType;
    public final ObjectNode data;

    public LoginModel(String login, String password, UserType userType) {
        this.login = login;
        this.password = password;
        this.userType = userType;
        data = new ObjectMapper().createObjectNode();
    }

    public String getUserTypeOperation() {
        return userType == UserType.CANDIDATE ? "LOGIN_CANDIDATE" : "LOGIN_RECRUITER";
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public ObjectNode getData() {
        return data;
    }

}
