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
    private String name;
    private String password;

    @JsonCreator
    public User(@JsonProperty("id") String id, @JsonProperty("name") String name, @JsonProperty("password") String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
