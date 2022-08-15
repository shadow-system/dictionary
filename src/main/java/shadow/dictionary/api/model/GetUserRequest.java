package shadow.dictionary.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetUserRequest {

    private final String login;

    @JsonCreator
    public GetUserRequest(@JsonProperty("login") String login) {
        this.login = login;
    }
}
