package request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckSaltRequest {
    private boolean doesSaltExist;

    public CheckSaltRequest() {

    }

    public  CheckSaltRequest(boolean doesSaltExist) {
        this.doesSaltExist = doesSaltExist;
    }

    @JsonProperty
    public boolean getDoesSaltExist() {
        return this.doesSaltExist;
    }

}
