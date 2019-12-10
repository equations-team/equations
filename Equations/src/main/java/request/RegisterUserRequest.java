package request;

import com.fasterxml.jackson.annotation.JsonProperty;
import entity.User;

public class RegisterUserRequest {
    private User user;

    public RegisterUserRequest() {}

    public RegisterUserRequest(User user) {
        this.user = user;
    }

    @JsonProperty
    public User getUser() {
        return this.user;
    }



}
