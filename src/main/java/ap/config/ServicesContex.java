package ap.config;

import ap.services.*;
import ap.services.servicesimpl.*;
import org.glassfish.jersey.server.BackgroundScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:util.properties")
@PropertySource(value = {"classpath:auth.properties"})
public class ServicesContex {

    @Bean
    public UserServices userServices() {
        return new UserServicesImpl();
    }

    @Bean
    public CreateXMLService createXMLService() {
        return new CreateXMLServiceImpl();
    }

    @Bean
    public RateServices rateServices() {
        return new RateServicesImpl();
    }

    @Bean
    public InfoUserService infoUserService() {
        return new InfoUserServiceImpl();
    }

    @Bean
    public WorkoutService workoutService(){
       return new WorkoutServiceImpl();
    }

    @Bean
    public ExerciseService exerciseService(){
        return new ExerciseServiceImpl();
    }
    @Bean
    public TryService tryService(){
        return new TryServiceImpl();
    }

}
