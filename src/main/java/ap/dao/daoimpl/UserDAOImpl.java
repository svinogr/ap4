package ap.dao.daoimpl;

import ap.dao.UserDAO;
import ap.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component
public class UserDAOImpl extends BasicDAOImpl<User> implements UserDAO  {


    public UserDAOImpl() {
        super(User.class);
    }


}
