package ap.services.servicesimpl;

import ap.config.HibernateConfig;
import ap.dao.UserDAO;
import ap.dao.UserRoleDAO;
import ap.entity.Role;
import ap.entity.User;
import ap.entity.UserRole;
import ap.services.MailService;
import ap.services.TokenService;
import ap.services.UserServices;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Date;

@Component
@Import(HibernateConfig.class)
public class UserServicesImpl implements UserServices {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserRoleDAO userRoleDAO;

    @Autowired
    MailService mailService;

    @Autowired
    Environment environment;

    @Autowired
    TokenService tokenService;

    @Override
    @Transactional
    public void registrationUser(User user) throws HibernateException {
        Date date = new Date();
        user.setDateRegistration(date);
        String userLogin = user.getLogin();
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        UserRole userRole = new UserRole(user.getLogin(), Role.ROLE_USER);
        System.out.println(user.toString());
        String mail = user.getEmail();
        userRoleDAO.add(userRole);
        userDAO.add(user);
        String token = tokenService.createToken(userLogin);
        mailService.sendSMTPforRegistration(mail, environment.getRequiredProperty("mail.linkregistration") + "token=" +
                token);
    }

    @Override
    @Transactional
    public String acceptRegistration(String token) {
        String responseMessage = null;
        String login = tokenService.loginUserByToken(token);
        System.err.println(login);
        if (login != null) {

            User acceptUser = userDAO.getByLogin(login);
            if (acceptUser != null) {
                tokenService.deleteToken(token);
                acceptUser.setActive(true);
                responseMessage = "регистрация подтверждена";

            }
        } else
            responseMessage = "профиль уже активирован или не был подтвержден во время, попробуйте зарегистрироватся  повторно";


        return responseMessage;
    }

    @Override
    @Transactional
    public User getUser(String login) {
        User user = userDAO.getByLogin(login);
        return user;
    }

    @Override
    @Transactional
    public User getUserByName(String name) {
        User user = userDAO.getByName(name);
        return user;
    }

    @Override
    @Transactional
    public void userUpdate(User user) {
        userDAO.update(user);
    }

    @Override
    public User getById(int id) {
        return userDAO.getById(id);
    }

    @Override
    public Boolean allow(int id) {
        boolean flag = false;
        User user = null;
        try {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (ClassCastException e) {
        }
        if (user.getId() == id) {
            flag = true;
        } else System.err.println("данная оперция не доступна этому пользователю");
        return flag;
    }

    @Override
    @Transactional
    public User getByEmail(String email) {
        return userDAO.getByEmail(email);

    }
}
