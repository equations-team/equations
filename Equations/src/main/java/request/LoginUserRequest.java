package request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginUserRequest {
    private String userName;
    private String userPassword;

    public LoginUserRequest() {}

    public LoginUserRequest(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    @JsonProperty
    public String getUserName() {
        return this.userName;
    }

    @JsonProperty
    public String getUserPassword() {
        return this.userPassword;
    }
}
