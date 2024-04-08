package utfpr.cc66c.core.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import utfpr.cc66c.core.models.LoginModel;

import java.io.IOException;

public class LoginModelSerializer extends JsonSerializer<LoginModel> {
    @Override
    public void serialize(LoginModel loginModel, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        var data = JsonNodeFactory.instance.objectNode();
        data.put("email", loginModel.email());
        data.put("password", loginModel.password());

        jsonGenerator.writeObjectField("data", data);
        jsonGenerator.writeEndObject();
    }
}
