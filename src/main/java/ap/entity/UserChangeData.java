package ap.entity;

public class UserChangeData {

    String email;
    String password;

    public UserChangeData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserChangeData() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


