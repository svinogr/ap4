package ap.dao.daoimpl;

import ap.dao.PersistentLoginsDAO;
import ap.entity.PersistentLogins;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PersistentLoginsDAOImpl extends BasicDAOImpl<PersistentLogins> implements PersistentLoginsDAO {
    @Autowired
    SessionFactory sessionFactory;
    public PersistentLoginsDAOImpl(){
        super(PersistentLogins.class);}

    @Override
    @Transactional
    public PersistentLogins getByLogin(String login) {
        PersistentLogins persistentLogin=null;
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(PersistentLogins.class);
            criteria.add(Restrictions.eq("login", login));
            persistentLogin = (PersistentLogins) criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return persistentLogin;
    }

    @Override
    @Transactional
    public PersistentLogins getByToken(String token) {
        PersistentLogins persistentLogin=null;
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(PersistentLogins.class);
            criteria.add(Restrictions.eq("token", token));
            persistentLogin = (PersistentLogins) criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return persistentLogin;
    }

}

