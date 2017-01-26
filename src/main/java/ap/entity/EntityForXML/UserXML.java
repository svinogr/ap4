package ap.entity.EntityForXML;

import ap.entity.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class UserXML {

    private List<WorkoutXML> workoutXMLs;
    private int id;
    private String login;
    private Date date;
    private int infoUser;

    public UserXML() {
    }

    public UserXML(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.date = user.getDateRegistration();
        this.infoUser = user.getUserInfo().getId();
    }

    public UserXML(List<WorkoutXML> workoutXMLs) {
        this.workoutXMLs = workoutXMLs;
    }

    @XmlElement(name = "workout")
    public void setWorkoutXMLs(List<WorkoutXML> workoutXMLs) {
        this.workoutXMLs = workoutXMLs;
    }

    public List<WorkoutXML> getWorkoutXMLs() {
        return workoutXMLs;
    }

    public int getId() {
        return id;
    }
    @XmlElement(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }
    @XmlElement(name = "login")
    public void setLogin(String login) {
        this.login = login;
    }

    public Date getDate() {
        return date;
    }

    @XmlElement(name = "date")
    public void setDate(Date date) {
        this.date = date;
    }

    public int getInfoUser() {
        return infoUser;
    }
    @XmlElement(name = "userInfo")
    public void setInfoUser(int infoUser) {
        this.infoUser = infoUser;
    }
}
