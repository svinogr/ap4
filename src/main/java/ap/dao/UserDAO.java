package ap.dao;

import ap.entity.User;

public interface UserDAO extends BasicDAO<User> {
public User getByLogin(String login);
}
