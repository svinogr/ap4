package ap.entity;

import ap.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

public class CustomUserServiceDetailsExtJdbcDaoImpl extends JdbcDaoImpl {

    @Autowired
    UserServices userServices;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = super.loadUserByUsername(username);
        User user = userServices.getUser(username);
        user.setUserDetails(userDetails);
        return user;
    }
}
