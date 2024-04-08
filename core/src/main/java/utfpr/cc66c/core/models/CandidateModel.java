package utfpr.cc66c.core.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import utfpr.cc66c.core.serializers.candidate.CandidateModelDeserializer;
import utfpr.cc66c.core.serializers.candidate.CandidateModelSerializer;

@JsonSerialize(using = CandidateModelSerializer.class)
@JsonDeserialize(using = CandidateModelDeserializer.class)
public record CandidateModel(String name, LoginModel login) {
}

