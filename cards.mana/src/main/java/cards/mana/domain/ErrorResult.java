package cards.mana.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ErrorResult {

    @JsonProperty
    private List<Error> errors;

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
