package request;

import com.fasterxml.jackson.annotation.JsonProperty;
import entity.User;

public class GetUserRequest {
    private String userId;

    public GetUserRequest() {}

    public GetUserRequest(String userId) {
        this.userId = userId;
    }

    @JsonProperty
    public String getUserId() {
        return this.userId;
    }



}
