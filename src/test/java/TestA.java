import ap.config.HibernateConfig;
import ap.entity.EntityForXML.UserXML;
import ap.entity.User;
import ap.services.CreateXMLService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = HibernateConfig.class)
public class TestA {
    @Autowired
    CreateXMLService createXMLService;

    @Test
    public void one() {
        String s = "<user><login>login</login><email>email</email><password>pass+</password></user>";

        User u = createXMLService.getUserFromXML(s);
        System.err.println(u.getLogin()+" "+u.getEmail()+" "+u.getPassword());


    }

}
