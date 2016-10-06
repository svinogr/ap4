package ap.config;

import ap.dao.BasicDAO;
import ap.dao.UserDAO;
import ap.dao.UserRoleDAO;

import ap.dao.daoimpl.BasicDAOImpl;
import ap.dao.daoimpl.UserDAOImpl;
/*import ap.dao.daoimpl.WorkoutContainerDAOImpl;*/
import ap.dao.daoimpl.UserRoleDAOImpl;
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
    public BasicDAO basicDAO(){
        return new BasicDAOImpl();
    }
/*
    @Bean
    public WorkoutContainerDAO workoutContainerDAO(){return  new WorkoutContainerDAOImpl();}*/

    @Bean
    public UserDAO userDAO(){
        return new UserDAOImpl();
    }

    @Bean
    public UserRoleDAO userRoleDAO(){return new UserRoleDAOImpl();}


}
