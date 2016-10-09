package ap.dao;

import ap.entity.UserRole;

public interface UserRoleDAO extends BasicDAO {
    public UserRole getRoleByUserLogin(String login);
}
