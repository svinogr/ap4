package ap.config;

import ap.dao.*;

import ap.dao.daoimpl.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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
    public WorkoutRatingDAO workoutRatingDAO() {
        return new WorkoutRatingDAOImpl();
    }

    @Bean
    PersistentLoginsDAO persistentLoginsDAO() {
        return new PersistentLoginsDAOImpl();
    }

    @Bean(name = "multipartResolver")
     CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver= new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(100000);
        return multipartResolver;
    }

    @Bean
    UserInfoDAO userInfoDAO(){
        return new UserInfoDAOImpl();
    }

}
