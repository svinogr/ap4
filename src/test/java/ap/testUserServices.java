package ap;


import ap.config.HibernateConfig;
import ap.entity.User;
import ap.services.UserServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = HibernateConfig.class)
public class testUserServices {
    @Autowired
    UserServices userServices;

    @Test
    public void UserRegistrationTest(){
        User user = new User();
        user.setLogin("test");
        user.setName("test");
        user.setPassword("test");
        user.setEmail("test@mail.ru");
        userServices.registrationUser(user);

    }

}
