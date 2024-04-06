package utfpr.cc66c.core.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import utfpr.cc66c.core.serializers.LoginModelDeserializer;
import utfpr.cc66c.core.serializers.LoginModelSerializer;
import utfpr.cc66c.core.types.UserType;

@JsonSerialize(using = LoginModelSerializer.class)
@JsonDeserialize(using = LoginModelDeserializer.class)
public record LoginModel(String login, String password, UserType userType) {

    public String getUserTypeOperation() {
        return userType == UserType.CANDIDATE ? "LOGIN_CANDIDATE" : "LOGIN_RECRUITER";
    }

}
