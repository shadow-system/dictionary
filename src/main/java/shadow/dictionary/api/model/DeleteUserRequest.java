package shadow.dictionary.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class DeleteUserRequest {

    private final String username;

    @JsonCreator
    public DeleteUserRequest(@JsonProperty("username") String username) {
        this.username = username;
    }
}
