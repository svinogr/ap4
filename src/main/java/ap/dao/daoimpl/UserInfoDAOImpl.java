package ap.dao.daoimpl;

import ap.dao.UserInfoDAO;
import ap.entity.User;
import ap.entity.UserInfo;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserInfoDAOImpl extends BasicDAOImpl<UserInfo> implements UserInfoDAO {
    @Autowired
    SessionFactory sessionfactory;
    @Override
    @Transactional
    public UserInfo getByLogin(String login) {
        UserInfo userInfo=null;
        try {
            Session session = sessionfactory.getCurrentSession();
            Criteria criteria = session.createCriteria(UserInfo.class);
            criteria.add(Restrictions.eq("login", login));
            userInfo = (UserInfo) criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return userInfo;
    }
}
