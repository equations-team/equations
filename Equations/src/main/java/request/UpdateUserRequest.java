package request;

import com.fasterxml.jackson.annotation.JsonProperty;
import entity.User;

public class UpdateUserRequest {
    private User user;

    public UpdateUserRequest() {
    }

    public UpdateUserRequest(User user) {
        this.user = user;
    }

    @JsonProperty
    public User getUser() {
        return this.user;
    }
}
