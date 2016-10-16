package ap.services;

import ap.entity.User;

public interface UserServices {
     public void registrationUser(User user);
     public User getUser(String login);
     public void userUpdate(User user);
     public User getById(int id);
}
