package utfpr.cc66c.core.serializers.candidate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import utfpr.cc66c.core.models.CandidateModel;

import java.io.IOException;


public class CandidateModelSerializer extends JsonSerializer<CandidateModel> {
    @Override
    public void serialize(CandidateModel candidateModel, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        var data = JsonNodeFactory.instance.objectNode();
        data.put("email", candidateModel.login().email());
        data.put("password", candidateModel.login().password());
        data.put("name", candidateModel.name());

        jsonGenerator.writeObjectField("data", data);
        jsonGenerator.writeEndObject();
    }
}
