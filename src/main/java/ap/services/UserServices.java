package ap.services;

import ap.entity.EntityForXML.UserXML;
import ap.entity.User;

import java.util.List;
import java.util.Map;

public interface UserServices {
/*    void registrationUser(User user);*/

    UserXML registrationUser(UserXML user);

    String acceptRegistration(String token);

    User getUser(String login);

    User getUserByName(String name);

    void userUpdate(User user);

    User getById(int id);

    Boolean allow(int id);

    User getByEmail(String email);

    Boolean changePassword(String login, String paswword);

    User getLoggedUser();

    List<User> getSearchUser(Map<String, String> map);
    boolean isAdmin();




}
