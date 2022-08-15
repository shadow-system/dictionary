package shadow.dictionary.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private String id;
    @Indexed(unique = true)
    private String login;
    private String password;

    @JsonCreator
    public User(@JsonProperty("id") String id, @JsonProperty("login") String login, @JsonProperty("password") String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
