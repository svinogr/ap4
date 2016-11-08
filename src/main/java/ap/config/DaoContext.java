package ap.config;

import ap.dao.*;

import ap.dao.daoimpl.*;
/*import ap.dao.daoimpl.WorkoutContainerDAOImpl;*/
import ap.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:util.properties")
@PropertySource(value = {"classpath:auth.properties"})
public class DaoContext {
    @Autowired
    Environment environment;

    @Autowired
    HibernateConfig hibernateConfig;

    @Bean
    public BasicDAO basicDAO() {
        return new BasicDAOImpl();
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAOImpl();
    }

    @Bean
    public UserRoleDAO userRoleDAO() {
        return new UserRoleDAOImpl();
    }

    @Bean
    public WorkoutDAO workoutDAO() {
        return new WorkoutDAOImpl();
    }

    @Bean
    public ExersiceDAO exersiceDAO() {
        return new ExerciseDAOImpl();
    }

    @Bean
    public TryDAO tryDAO() {
        return new TryDAOImpl();
    }

    @Bean
    public  WorkoutRatingDAO workoutRatingDAO(){
        return new WorkoutRatingDAOImpl();
    }
}
