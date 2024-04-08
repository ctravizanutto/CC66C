package utfpr.cc66c.core.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import utfpr.cc66c.core.serializers.recruiter.RecruiterModelDeserializer;
import utfpr.cc66c.core.serializers.recruiter.RecruiterModelSerializer;

@JsonSerialize(using = RecruiterModelSerializer.class)
@JsonDeserialize(using = RecruiterModelDeserializer.class)
public record RecruiterModel(String name, String description, String industry,
                             LoginModel login) implements SignupModel {
    @Override
    public LoginModel getLoginModel() {
        return login;
    }
}
