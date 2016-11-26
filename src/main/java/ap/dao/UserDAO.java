package ap.dao;

import ap.entity.User;

public interface UserDAO extends BasicDAO<User> {
    User getByLogin(String login);

    User getByName(String name);
}
