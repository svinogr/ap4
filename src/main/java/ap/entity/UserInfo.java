package ap.entity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

@Entity()
@Table(name = "users_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "age")
    private int age;
    @Column(name = "weight")
    private int weight;
    @Column(name = "height")
    private int height;
    @Column(name = "experience")
    private int experience;





    

    public int getId() {
        return id;
    }

    public UserInfo() {
    }

    public UserInfo(String login, int age, int weight, int height, int experience) {
        this.login = login;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.experience = experience;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                ", experience=" + experience +
                '}';
    }
}
