package ap;

import ap.config.HibernateConfig;
import ap.dao.UserDAO;
import ap.entity.User;
import ap.entity.Workout;
import ap.services.CreateXMLService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.StringWriter;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = HibernateConfig.class)
public class XMLTest {

    @Autowired
    CreateXMLService createXMLService;

    @Autowired
    UserDAO userDAO;

    @Test
    @Transactional
    public void getObjectXML(){
       User user = userDAO.getById(1);
        StringWriter s = createXMLService.getXML(user);

    }
}
