package ap.services.servicesimpl;

import ap.config.HibernateConfig;
import ap.dao.UserDAO;
import ap.dao.UserRoleDAO;
import ap.entity.Role;
import ap.entity.User;
import ap.entity.UserRole;
import ap.services.UserServices;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
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

    @Override
    @Transactional
    public void registrationUser(User user) throws HibernateException {
        Date date = new Date();
        user.setDateRegistration(date);
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        UserRole userRole = new UserRole(user.getLogin(), Role.ROLE_USER);
        System.out.println(user.toString());
        userRoleDAO.add(userRole);
        userDAO.add(user);
    }

    @Override
    @Transactional
    public User getUser(String login) {
        User user = userDAO.getByLogin(login);
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
}
