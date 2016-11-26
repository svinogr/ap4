package ap.services;

import ap.entity.User;

public interface UserServices {
    void registrationUser(User user);

    User getUser(String login);

    User getUserByName(String name);

    void userUpdate(User user);

    User getById(int id);

    Boolean allow(int id);
}
