package ap.dao;

import ap.entity.UserRole;

public interface UserRoleDAO extends BasicDAO {
    UserRole getRoleByUserLogin(String login);
}
