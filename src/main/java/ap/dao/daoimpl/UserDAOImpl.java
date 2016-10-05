package ap.dao.daoimpl;

import ap.dao.UserDAO;
import ap.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAOImpl extends BasicDAOImpl<User> implements UserDAO  {


    public UserDAOImpl() {
        super(User.class);
    }
}
