package ap.dao.daoimpl;

import ap.dao.UserDAO;
import ap.entity.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class UserDAOImpl extends BasicDAOImpl<User> implements UserDAO  {
    @Autowired
    SessionFactory sessionfactory;

    public UserDAOImpl() {
        super(User.class);
    }


    @Override
    @Transactional
    public User getByLogin(String login) {
        User user=null;
        try {
            Session session = sessionfactory.getCurrentSession();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("login", login));
            user = (User) criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return user;
    }
}
