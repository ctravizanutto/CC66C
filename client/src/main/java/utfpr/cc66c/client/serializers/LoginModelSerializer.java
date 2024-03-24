package utfpr.cc66c.client.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import utfpr.cc66c.client.models.LoginModel;

import java.io.IOException;

public class LoginModelSerializer extends JsonSerializer<LoginModel> {
    @Override
    public void serialize(LoginModel loginModel, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("login", loginModel.getLogin());
        jsonGenerator.writeStringField("password", loginModel.getPassword());
        jsonGenerator.writeObjectField("data", loginModel.getData());
        jsonGenerator.writeEndObject();
    }
}
