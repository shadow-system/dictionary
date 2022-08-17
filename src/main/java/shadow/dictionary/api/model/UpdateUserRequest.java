package shadow.dictionary.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UpdateUserRequest {

    private final String oldUsername;
    private final String username;
    private final String password;

    @JsonCreator
    public UpdateUserRequest(
        @JsonProperty("oldUsername") String oldUsername,
        @JsonProperty("username") String username,
        @JsonProperty("password") String password) {
        this.oldUsername = oldUsername;
        this.username = username;
        this.password = password;
    }
}
