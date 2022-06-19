package shadow.dictionary.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:mongodb.properties")
@ConfigurationProperties(prefix = "connection")
@NoArgsConstructor
@Getter
@Setter
public class MongoConfig {

    private String username;
    private String password;
    private String databaseName;
    private String host;
}
