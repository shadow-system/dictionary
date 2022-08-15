package shadow.dictionary.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class DeleteUserRequest {

    private final String login;

    @JsonCreator
    public DeleteUserRequest(@JsonProperty("login") String login) {
        this.login = login;
    }
}
