package utfpr.cc66c.core.serializers.recruiter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.models.LoginModel;
import utfpr.cc66c.core.models.RecruiterModel;
import utfpr.cc66c.core.types.UserType;

import java.io.IOException;

public class RecruiterModelDeserializer extends JsonDeserializer<RecruiterModel> {
    @Override
    public RecruiterModel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode json = jsonParser.getCodec().readTree(jsonParser);
        var data = (ObjectNode) json.get("data");

        var name = data.get("name").asText();
        var password = data.get("password").asText();
        var email = data.get("email").asText();
        var industry = data.get("industry").asText();
        var description = json.get("description").asText();

        return new RecruiterModel(name, description, industry, new LoginModel(email, password, UserType.RECRUITER));
    }
}
