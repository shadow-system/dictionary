package shadow.dictionary.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetUserRequest {

    private final String username;

    @JsonCreator
    public GetUserRequest(@JsonProperty("username") String username) {
        this.username = username;
    }
}
