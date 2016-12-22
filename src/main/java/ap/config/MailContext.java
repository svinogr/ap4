package ap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;

@Configuration
@PropertySource(value = {"classpath:mail.properties"})
public class MailContext {
    @Autowired
    Environment environment;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername(environment.getRequiredProperty("mail.login"));
        javaMailSender.setPassword(environment.getRequiredProperty("mail.password"));
        javaMailSender.setPort(Integer.parseInt(environment.getRequiredProperty("mail.port")));
        javaMailSender.setHost(environment.getRequiredProperty("mail.host"));
        javaMailSender.setDefaultEncoding("windows-1251");
        Properties properties = new Properties();
        properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.smtp.starttls.enable", environment.getRequiredProperty("mail.tls"));
        properties.setProperty("mail.smtp.ssl.enable", environment.getRequiredProperty("mail.ssl"));
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }


}
