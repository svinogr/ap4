package ap.services.servicesimpl;


import ap.dao.PersistentLoginsDAO;
import ap.entity.PersistentLogins;
import ap.services.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.hibernate.HibernateException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.sql.Date;

@Component
public class TokenServiceImpl implements TokenService {
    @Autowired
    Environment environment;
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    PersistentLoginsDAO persistentLoginsDAO;

    /**
     * The method create token for authentication of user
     *
     * @param login
     * @return
     * @throws HibernateException
     */
    @Override
    @Transactional
    public String createToken(String login) throws HibernateException {
        Key key = MacProvider.generateKey();
        String compactJws = Jwts.builder()
                .setIssuer(environment.getRequiredProperty("mail.site"))
                .setSubject(login)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        saveToken(login, compactJws);
        return compactJws;

    }


    @Override
    @Transactional
    public String loginUserByToken(String token) {
        PersistentLogins persistentLogins = persistentLoginsDAO.getByToken(token);
        try {
            return persistentLogins.getLogin();
        } catch (NullPointerException e) {
            System.err.println("не получено имя пользователя по токену" + e);
            return null;
        }

    }

    @Override
    @Transactional
    public void deleteToken(String token) {
        PersistentLogins persistentLogins = persistentLoginsDAO.getByToken(token);
        System.err.println("ffffffffffffffffffffffff"+persistentLogins);
        if (persistentLogins!=null){
        persistentLoginsDAO.delete(persistentLogins);}

    }

    @Transactional
    private void saveToken(String login, String token) throws HibernateException {
        PersistentLogins persistentLogins = persistentLoginsDAO.getByLogin(login);
        if (persistentLogins != null) {
            persistentLogins.setToken(token);
        } else {
            PersistentLogins persistent_logins = new PersistentLogins();
            persistent_logins.setLogin(login);
            persistent_logins.setToken(token);
            persistent_logins.setLastused(new Date(System.currentTimeMillis()).toString());
            System.err.println(persistent_logins);
            persistentLoginsDAO.add(persistent_logins);
        }
    }

}
