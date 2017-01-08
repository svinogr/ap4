package ap.dao;

import ap.entity.UserInfo;

public interface UserInfoDAO extends BasicDAO<UserInfo> {
   UserInfo getByLogin(String login);
}
