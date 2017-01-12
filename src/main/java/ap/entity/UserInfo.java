package ap.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Arrays;

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

    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UserInfo(String login, int age, int height, int weight, int experience, byte[] image) {
        this.login = login;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.experience = experience;

    }


    @Column(name = "image", length = 5120000)
    private String image;


    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public UserInfo() {
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
                ", description='" + description + '\'' +
                ", image=" +
                '}';
    }
}
