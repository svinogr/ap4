package ap.dao;

import ap.entity.PersistentLogins;
import ap.entity.User;

public interface PersistentLoginsDAO extends BasicDAO<PersistentLogins> {
    PersistentLogins getByLogin(String login);
    PersistentLogins getByToken(String token);
}
