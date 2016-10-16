package ap.config;

import ap.services.CreateXMLService;
import ap.services.UserServices;
import ap.services.servicesimpl.CreateXMLServiceImpl;
import ap.services.servicesimpl.UserServicesImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:util.properties")
@PropertySource(value = {"classpath:auth.properties"})
public class ServicesContex {

    @Bean
    public UserServices userServices(){return new UserServicesImpl();}
 @Bean
    public CreateXMLService createXMLService(){return new CreateXMLServiceImpl();}


}
