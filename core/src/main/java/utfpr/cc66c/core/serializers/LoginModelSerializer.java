package utfpr.cc66c.core.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import utfpr.cc66c.core.models.LoginModel;

import java.io.IOException;

public class LoginModelSerializer extends JsonSerializer<LoginModel> {
    @Override
    public void serialize(LoginModel loginModel, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        var data = new ObjectMapper().createObjectNode();
        data.put("email", loginModel.getLogin());
        data.put("password", loginModel.getPassword());

        jsonGenerator.writeStringField("operation", loginModel.getUserTypeOperation());
        jsonGenerator.writeObjectField("data", data);
        jsonGenerator.writeEndObject();
    }
}
