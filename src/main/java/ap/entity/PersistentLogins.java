package ap.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "token")
public class PersistentLogins implements Serializable {
    @Column(name = "login", unique = true)
    String login;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "series")
    int id;
    @Column(name = "token")
    String token;
    @Column(name = "last_used")
    String lastused;

    public PersistentLogins(int id, String username, String token, String lastused) {
        this.id= id;
        this.login = username;
        this.token = token;
        this.lastused = lastused;
    }


    public PersistentLogins() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLastused() {
        return lastused;
    }

    public void setLastused(String lastused) {
        this.lastused = lastused;
    }

    @Override
    public String toString() {
        return "PersistentLogins{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", token='" + token + '\'' +
                ", lastused='" + lastused + '\'' +
                '}';
    }
}
