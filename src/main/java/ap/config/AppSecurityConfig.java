package ap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan({"ap"})
@PropertySource(value = {"classpath:auth.properties"})
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //TODO не забыть убрать тест и поставить правильный URL(логин и логаут)
        http.authorizeRequests()
                .antMatchers("/map/rest/hello/**").access("hasRole('ADMIN')")
                .antMatchers("/confidential/**").access("hasRole('USER')")
                .antMatchers("/superconfidential/**").access("hasRole('SUPERADMIN')")
                .and().formLogin().loginPage("/login").permitAll().
                defaultSuccessUrl("/test", false)
                .and().csrf().disable().
                sessionManagement().maximumSessions(100).sessionRegistry(sessionRegistry()).and()
                .and().logout().
                logoutUrl("/logout").logoutSuccessUrl("/test").
                invalidateHttpSession(true).deleteCookies();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}

