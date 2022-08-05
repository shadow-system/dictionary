package shadow.dictionary.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

//TODO: websecurityconfig depreated, investigation needed for customizer appoach
@EnableWebSecurity
@Configuration
public class WebSecurityConfig implements WebSecurityCustomizer {

    @Override
    public void customize(WebSecurity web) {

    }
}
