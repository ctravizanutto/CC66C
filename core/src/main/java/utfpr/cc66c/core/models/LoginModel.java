package utfpr.cc66c.core.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import utfpr.cc66c.core.serializers.LoginModelSerializer;
import utfpr.cc66c.core.types.UserType;

@JsonSerialize(using = LoginModelSerializer.class)
public record LoginModel(String email, String password, UserType userType) {

    public String getLoginOperation() {
        return userType == UserType.CANDIDATE ? "LOGIN_CANDIDATE" : "LOGIN_RECRUITER";
    }

    public String getSingUpOperation() {
        return userType == UserType.CANDIDATE ? "SIGNUP_CANDIDATE" : "SIGNUP_RECRUITER";
    }

}
