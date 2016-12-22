import ap.config.HibernateConfig;
import ap.dao.PersistentLoginsDAO;
import ap.entity.PersistentLogins;
import io.jsonwebtoken.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.sql.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = HibernateConfig.class)
public class tes {
    @Autowired
    PersistentLoginsDAO persistentLoginsDAO;

    @Test
    public void createJWT() {

        Key key = MacProvider.generateKey();

        String compactJws = Jwts.builder().setIssuer("wwww.ddd.ru/")
                .setSubject("Joe")
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        System.out.println("токен" + compactJws);

    }

    @Test
    @Transactional()
    public void saveToken() {
        PersistentLogins persistent_logins = new PersistentLogins();
        persistent_logins.setLogin("test");
        persistent_logins.setToken("token");
        persistent_logins.setLastused(new Date(System.currentTimeMillis()).toString());
        System.err.println(persistent_logins);
        persistentLoginsDAO.add(persistent_logins);
        System.err.println(persistentLoginsDAO.getByLogin("test"));
    }


}
