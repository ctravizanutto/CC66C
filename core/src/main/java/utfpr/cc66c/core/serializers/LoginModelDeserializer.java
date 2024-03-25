package utfpr.cc66c.core.serializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import utfpr.cc66c.core.models.LoginModel;
import utfpr.cc66c.core.types.UserType;

import java.io.IOException;

public class LoginModelDeserializer extends JsonDeserializer<LoginModel> {

    @Override
    public LoginModel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        var login = node.get("login").asText();
        var password = node.get("password").asText();

        return new LoginModel(login, password, UserType.CANDIDATE);
    }
}
