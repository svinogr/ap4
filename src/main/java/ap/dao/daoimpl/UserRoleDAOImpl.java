package ap.dao.daoimpl;

import ap.dao.UserRoleDAO;
import ap.entity.UserRole;

public class UserRoleDAOImpl extends BasicDAOImpl implements UserRoleDAO {
    public UserRoleDAOImpl() {
        super(UserRole.class);
    }
}
