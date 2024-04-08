package utfpr.cc66c.core.serializers.candidate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.models.CandidateModel;
import utfpr.cc66c.core.models.LoginModel;
import utfpr.cc66c.core.types.UserType;

import java.io.IOException;

public class CandidateModelDeserializer extends JsonDeserializer<CandidateModel> {
    @Override
    public CandidateModel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode json = jsonParser.getCodec().readTree(jsonParser);
        var data = (ObjectNode) json.get("data");
        var name = data.get("name").asText();
        var password = data.get("password").asText();
        var email = data.get("email").asText();

        return new CandidateModel(name, new LoginModel(email, password, UserType.CANDIDATE));
    }
}
