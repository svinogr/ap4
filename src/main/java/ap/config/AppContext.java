package ap.config;

import ap.entity.CustomUserServiceDetailsExtJdbcDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetailsService;




@Configuration
@PropertySource(value = {"classpath:auth.properties"})
@PropertySource(value = {"classpath:mail.properties"})
public class AppContext {
    @Autowired
    Environment environment;

    @Autowired
    HibernateConfig hibernateConfig;

    @Bean
    public UserDetailsService userDetailsService() {
        CustomUserServiceDetailsExtJdbcDaoImpl jdbcImpl = new CustomUserServiceDetailsExtJdbcDaoImpl();
        jdbcImpl.setDataSource(hibernateConfig.dataSource());
        jdbcImpl.setUsersByUsernameQuery(environment.getRequiredProperty("usersByQuery"));
        jdbcImpl.setAuthoritiesByUsernameQuery(environment.getRequiredProperty("rolesByQuery"));
        return jdbcImpl;
    }



}
