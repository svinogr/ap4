package ap.config;

import ap.services.TokenService;
import ap.services.servicesimpl.TokenServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:mail.properties"})
public class TokenContext {
    @Bean
    public TokenService tokenService(){
        return new TokenServiceImpl();
    }
}
