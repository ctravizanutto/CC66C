package utfpr.cc66c.client.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.client.serializers.LoginModelDeserializer;
import utfpr.cc66c.client.serializers.LoginModelSerializer;

@JsonSerialize(using = LoginModelSerializer.class)
@JsonDeserialize(using = LoginModelDeserializer.class)
public class LoginModel {
    public String login;
    public String password;
    public ObjectNode data;

    public LoginModel(String login, String password) {
        this.login = login;
        this.password = password;
        data = new ObjectMapper().createObjectNode();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ObjectNode getData() {
        return data;
    }

    public void setData(ObjectNode data) {
        this.data = data;
    }
}

