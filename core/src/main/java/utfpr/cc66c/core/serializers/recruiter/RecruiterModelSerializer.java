package utfpr.cc66c.core.serializers.recruiter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import utfpr.cc66c.core.models.RecruiterModel;

import java.io.IOException;

public class RecruiterModelSerializer extends JsonSerializer<RecruiterModel> {
    @Override
    public void serialize(RecruiterModel recruiterModel, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        var data = JsonNodeFactory.instance.objectNode();
        data.put("email", recruiterModel.login().email());
        data.put("password", recruiterModel.login().password());
        data.put("name", recruiterModel.name());
        data.put("description", recruiterModel.description());
        data.put("industry", recruiterModel.industry());

        jsonGenerator.writeObjectField("data", data);
        jsonGenerator.writeEndObject();
    }
}
