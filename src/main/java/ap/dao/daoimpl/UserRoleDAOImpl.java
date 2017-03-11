package ap.dao.daoimpl;

import ap.dao.UserRoleDAO;
import ap.entity.User;
import ap.entity.UserRole;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class UserRoleDAOImpl extends BasicDAOImpl implements UserRoleDAO {
    public UserRoleDAOImpl() {
        super(UserRole.class);
    }

    public UserRole getRoleByUserLogin(String login){
       UserRole userRole=null;
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(UserRole.class);
            criteria.add(Restrictions.eq("login", login));
            userRole = (UserRole) criteria.uniqueResult();
            System.out.println(userRole.toString());
        } catch (HibernateException e){}
        return userRole;
    }
}
