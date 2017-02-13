package ap.entity.EntityForXML;

import ap.entity.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class UserXML {

    private List<WorkoutXML> workoutXML = new ArrayList<>(0);
    private int userInfoId;
    private int myUserId;
    private Date date;
    @NotEmpty
    @NotNull
    private String login;
    @Email(message = "email введен некоректно")
    private String email;
    @NotEmpty
    @NotNull
    private String password;

    public UserXML() {
    }

    public UserXML(User user) {
        this.myUserId = user.getId();
        this.userInfoId = user.getUserInfo().getId();
        this.date = user.getDateRegistration();
    }

    public UserXML(User user, int userInfoId) {
        this.myUserId = user.getId();
        this.userInfoId = userInfoId;
        this.date = user.getDateRegistration();
    }

    public List<WorkoutXML> getWorkoutXML() {
        return workoutXML;
    }
    @XmlElement(name = "workoutXML")
    public void setWorkoutXML(List<WorkoutXML> workoutXML) {
        this.workoutXML = workoutXML;
    }

    public int getUserInfoId() {
        return userInfoId;
    }
    @XmlElement(name = "userInfoId")
    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public int getUserId() {
        return myUserId;
    }

    @XmlElement(name = "myUserId")
    public void setUserId(int userId) {
        this.myUserId = userId;
    }

    public Date getDate() {
        return date;
    }
    @XmlElement(name = "date")
    public void setDate(Date date) {
        this.date = date;
    }



    public String getLogin() {
        return login;
    }
    @XmlElement(name = "login")
    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }
    @XmlElement(name = "email")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    @XmlElement(name = "password")
    public void setPassword(String password) {
        this.password = password;
    }
}
