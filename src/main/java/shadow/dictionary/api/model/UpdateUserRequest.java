package shadow.dictionary.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UpdateUserRequest {

    private final String oldLogin;
    private final String login;
    private final String password;

    @JsonCreator
    public UpdateUserRequest(
        @JsonProperty("oldLogin") String oldLogin,
        @JsonProperty("login") String login,
        @JsonProperty("password") String password) {
        this.oldLogin = oldLogin;
        this.login = login;
        this.password = password;
    }
}
