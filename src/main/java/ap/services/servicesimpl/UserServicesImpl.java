package ap.services.servicesimpl;

import ap.config.HibernateConfig;
import ap.dao.UserDAO;
import ap.dao.UserInfoDAO;
import ap.dao.UserRoleDAO;
import ap.entity.EntityForXML.UserXML;
import ap.entity.Role;
import ap.entity.User;
import ap.entity.UserInfo;
import ap.entity.UserRole;
import ap.services.MailService;
import ap.services.TokenService;
import ap.services.UserServices;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Import(HibernateConfig.class)
public class UserServicesImpl implements UserServices {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    UserInfoDAO userInfoDAO;

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

  /*  @Override
    @Transactional
    public void registrationUser(User user) throws HibernateException {
        Date date = new Date();
        user.setDateRegistration(date);

        String userLogin = user.getLogin();

        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        String mail = user.getEmail();
        UserInfo userInfo = new UserInfo();
        UserRole userRole = new UserRole(userLogin, Role.ROLE_USER);
        userRoleDAO.add(userRole);
        user.setPassword(password);

        userDAO.add(user);

        userInfo.setLogin(userLogin);
        userInfo.setName("Еще нет имени");
        userInfo.setDescription("Расскажите немного о себе");
        // String imageDefault = environment.getRequiredProperty("info.avatardefault");
        userInfo.setImage("R0lGODlhZABkAMQAAAAAAP////f6/uXw++71/KzR8rbW88Dc9dPm+Nzr+XO163+77IvA7ZbG76HL8Mnh9mav6f///wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAEAABEALAAAAABkAGQAAAX/YCCOZGmeaKqubOu+cCzPdG3feK7vfO//wKBwSCwaj8ikcslsOp9QogJCpTYG0aWCQWpAEtnklkSAFMLI8ah8RhvVooaCIIKPGNySA7IgIagIIg8MVAwPAQRegX+LEA10g4WHf1VVAgGREIYwU5UQBiN2IngmexCHcYABBxAHIganInsIjA8QDiKsrqutI6xYub28uy12rG2iAaR6mnkDmhCBCg0kDH0iBbfauHXcsgq+EMABCt4BDuAuotl0nVaByyR7tlgGC6wIz8QBsHSvVaBEPPNExd8vgQQLqssTDgscOQLijdgjYIEDAQoM4NNHop8IAgumKFjgjyOKgwFM/85YB6EdQ0YSZUEQcEABKwL4yFG7cw2koVPW/NlpcI3XuKFFWRgzU4chxJjnZmK8xSuQrn+HQC4QwCgiSUxUeaEyOsIWN1ZjlXpiQMydI3gMJ86MajWaIEKaJi34yiiA10CZNjUkETitm8OIEytezLix48eQIwvBaE4yjgGm+IyzfCOBMM48GAQEvaPMZtImBBAMlNILBJuCFCDA68DfKndtBlZ5VFWEZwWqV+cMovqsXc87Hyg4U1M2Ijl0hg9YnvLTx2m9lb0eoXI4ENUBc8rp2BIWMTaISpB6FpAA9ZwH9qarTsz7D8+7cmIf8ecBrM0L5PFAZppU5wkWG0XDwP983Xky2g62gNHbfiL0Rw8JAd5m23rWIWJNVQzgsiB3nw2HQDk9jBdMICr+Q0AZ5zGV4R1csDfCf6wQdcmICNVn1yhJXdZhb8jF1kYBziUyRwBEAeNZjR16xUssyjBY4o8nVmYDJQmBMoBrsIVDRW0iCODaJxxWcgUvO1VJoo+VPIhDXyTYiJoMdJIo55189unnn4AGKuighBZq6KGI+pUNFbsQGOZtVgBDkaJV7MNYRQzQ8QcokwaQDRhyBGLmkpMuuAh1jrEi4TngdMoOfiNgdAZFsLroGDonEEiVqtX0QSBrYAG7GK6lzDUMrzxFxdWPwab6xUStGltcrX5RRxH/jDe25BimmlrX6VWh+gWdsp46d2Ib2y4qjK71dbImuVN+lui89NZr77345qvvvvz266+gmTgiVCUMsPZlFQXzQxAXAfMWVU2MBuAZus9oCYRnAUlJDkM8UriHbcRKHOWHUQFjiyst/meEbf9cAseHl/jBbMgsK3xJp0z20Z8IMxoxACyVuOzJLgLYUwmwIacEtCXwKkzHAtTsbIRnY8HislPiVIRuniFTne3NxtrMCwEOBDlENjGLDccfB9gCLNfzof1100SVmVFGSKBV5gJzwbHHABOPoAi04aBS0Vx7tKG3TNoisbQDk7q1ACrKWZEN0vMpPOakewA9OQmeWTwoJM4lPCOsoaSTEB+9qV+3Z6Gt79Hmv7TXbvvtuOeu++68995vCAA7");
        userInfo.setParentid(user);
        userInfoDAO.add(userInfo);
        String token = tokenService.createToken(userLogin);
        mailService.sendSMTPforRegistration(mail, environment.getRequiredProperty("mail.linkregistration") + "token=" +
                token);
    }*/

    @Override
    @Transactional
    public UserXML registrationUser(UserXML user) throws HibernateException {
        User createUser = new User();
        Date date = new Date();
        String userLogin = user.getLogin();
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        String mail = user.getEmail();


        createUser.setPassword(password);
        createUser.setDateRegistration(date);
        createUser.setEmail(mail);
        createUser.setLogin(userLogin);
        int newid = userDAO.add(createUser);

        UserRole userRole = new UserRole(newid, Role.ROLE_USER, userLogin);
        userRoleDAO.add(userRole);
        UserInfo userInfo = new UserInfo();
        userInfo.setLogin(userLogin);
        userInfo.setName("Еще нет имени");
        userInfo.setDescription("Расскажите немного о себе");
        // String imageDefault = environment.getRequiredProperty("info.avatardefault");
        userInfo.setImage("R0lGODlhZABkAMQAAAAAAP////f6/uXw++71/KzR8rbW88Dc9dPm+Nzr+XO163+77IvA7ZbG76HL8Mnh9mav6f///wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAEAABEALAAAAABkAGQAAAX/YCCOZGmeaKqubOu+cCzPdG3feK7vfO//wKBwSCwaj8ikcslsOp9QogJCpTYG0aWCQWpAEtnklkSAFMLI8ah8RhvVooaCIIKPGNySA7IgIagIIg8MVAwPAQRegX+LEA10g4WHf1VVAgGREIYwU5UQBiN2IngmexCHcYABBxAHIganInsIjA8QDiKsrqutI6xYub28uy12rG2iAaR6mnkDmhCBCg0kDH0iBbfauHXcsgq+EMABCt4BDuAuotl0nVaByyR7tlgGC6wIz8QBsHSvVaBEPPNExd8vgQQLqssTDgscOQLijdgjYIEDAQoM4NNHop8IAgumKFjgjyOKgwFM/85YB6EdQ0YSZUEQcEABKwL4yFG7cw2koVPW/NlpcI3XuKFFWRgzU4chxJjnZmK8xSuQrn+HQC4QwCgiSUxUeaEyOsIWN1ZjlXpiQMydI3gMJ86MajWaIEKaJi34yiiA10CZNjUkETitm8OIEytezLix48eQIwvBaE4yjgGm+IyzfCOBMM48GAQEvaPMZtImBBAMlNILBJuCFCDA68DfKndtBlZ5VFWEZwWqV+cMovqsXc87Hyg4U1M2Ijl0hg9YnvLTx2m9lb0eoXI4ENUBc8rp2BIWMTaISpB6FpAA9ZwH9qarTsz7D8+7cmIf8ecBrM0L5PFAZppU5wkWG0XDwP983Xky2g62gNHbfiL0Rw8JAd5m23rWIWJNVQzgsiB3nw2HQDk9jBdMICr+Q0AZ5zGV4R1csDfCf6wQdcmICNVn1yhJXdZhb8jF1kYBziUyRwBEAeNZjR16xUssyjBY4o8nVmYDJQmBMoBrsIVDRW0iCODaJxxWcgUvO1VJoo+VPIhDXyTYiJoMdJIo55189unnn4AGKuighBZq6KGI+pUNFbsQGOZtVgBDkaJV7MNYRQzQ8QcokwaQDRhyBGLmkpMuuAh1jrEi4TngdMoOfiNgdAZFsLroGDonEEiVqtX0QSBrYAG7GK6lzDUMrzxFxdWPwab6xUStGltcrX5RRxH/jDe25BimmlrX6VWh+gWdsp46d2Ib2y4qjK71dbImuVN+lui89NZr77345qvvvvz266+gmTgiVCUMsPZlFQXzQxAXAfMWVU2MBuAZus9oCYRnAUlJDkM8UriHbcRKHOWHUQFjiyst/meEbf9cAseHl/jBbMgsK3xJp0z20Z8IMxoxACyVuOzJLgLYUwmwIacEtCXwKkzHAtTsbIRnY8HislPiVIRuniFTne3NxtrMCwEOBDlENjGLDccfB9gCLNfzof1100SVmVFGSKBV5gJzwbHHABOPoAi04aBS0Vx7tKG3TNoisbQDk7q1ACrKWZEN0vMpPOakewA9OQmeWTwoJM4lPCOsoaSTEB+9qV+3Z6Gt79Hmv7TXbvvtuOeu++68995vCAA7");
        userInfo.setParentid(createUser);
        int idUserInfo = userInfoDAO.add(userInfo);

        String token = tokenService.createToken(userLogin);
        mailService.sendSMTPforRegistration(mail, environment.getRequiredProperty("mail.linkregistration") + "token=" +
                token);

        user.setPassword(null);
        user.setDate(date);
        user.setUserId(newid);
        user.setUserInfoId(idUserInfo);
        return user;

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
    public Boolean acceptRegistrationHTML(String token) {
        Boolean responseMessage = false;
        String login = tokenService.loginUserByToken(token);
        System.err.println(login);
        if (login != null) {

            User acceptUser = userDAO.getByLogin(login);
            if (acceptUser != null) {
              //  tokenService.deleteToken(token);
                acceptUser.setActive(true);
                responseMessage = true;

            }
        } else
            responseMessage = false;


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
    @Transactional
    public User getById(int id) throws HibernateException, NullPointerException {
        if (userDAO.checkItBD(id)) {
            return userDAO.getById(id);
        }
        return null;
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

    @Override
    @Transactional
    public Boolean changePassword(String login, String password) {

        User user = this.getUser(login);
        if (user != null) {
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            this.userUpdate(user);
            return true;
        }
        return false;
    }
    @Override
    @Transactional
    public boolean changePassword(String password) {
        User user = getLoggedUser();
        user = userDAO.getByLogin(user.getLogin());
        if (user != null) {
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            this.userUpdate(user);
            return true;
        }
        return false;
    }

    @Override
    public User getLoggedUser() {
        User user = null;
        try {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (ClassCastException e) {
        }
        return user;
    }

    @Override
    @Transactional
    public List<User> getSearchUser(Map<String, String> map) {
        System.err.println(map.entrySet().toString());
        return userDAO.getSearchResultOneParameter(map);

    }

    @Override
    public boolean isAdmin() {
        User user = getLoggedUser();
        for (GrantedAuthority rol : user.getAuthorities()) {
            if (rol.getAuthority().equals(Role.ROLE_ADMIN.toString())) {
                return true;
            }
        }

        return false;
    }
}
