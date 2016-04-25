package cards.mana.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error {

    @JsonProperty
    private int code;

    @JsonProperty
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
