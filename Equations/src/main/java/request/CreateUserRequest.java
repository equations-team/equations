package request;

import com.fasterxml.jackson.annotation.JsonProperty;
import entity.User;

public class CreateUserRequest {
    private User user;

    public CreateUserRequest() {}

    public CreateUserRequest(User user) {
        this.user = user;
    }

    @JsonProperty
    public User getUser() {
        return this.user;
    }



}
