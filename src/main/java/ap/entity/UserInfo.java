package ap.entity;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Arrays;

@Entity()
@Table(name = "users_info")
public class UserInfo implements Imagable, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   protected int id;

    @Column(name = "login", nullable = false)
    protected String login;

    @Column(name = "name")
    protected String name;

    @Column(name = "age")
    protected int age;

    @Column(name = "weight")
    protected int weight;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentid")
    protected User parentid;

    @Column(name = "height")
    protected int height;

    @Column(name = "experience")
    protected int experience;

    @Column(name = "description")
    protected String description;

    @Column(name = "image", length = 5120000)
    protected String image;

    public UserInfo() {
    }

    public UserInfo(UserInfo userInfo) {
        this.login = userInfo.getLogin();
        this.name = userInfo.getName();
        this.age = userInfo.getAge();
        this.weight = userInfo.getWeight();
        this.parentid = userInfo.getParentid();
        this.height = userInfo.getHeight();
        this.experience = userInfo.getExperience();
        this.description = userInfo.getDescription();
        this.image = userInfo.getImage();
        this.id = userInfo.getId();
    }

    public int getId() {
        return id;
    }
    @XmlElement(name = "UserInfoId")
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

    public String getName() {
        return name;
    }
    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    @XmlElement(name = "age")
    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }
    @XmlElement(name = "weight")
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }
    @XmlElement(name = "height")
    public void setHeight(int height) {
        this.height = height;
    }

    public int getExperience() {
        return experience;
    }
    @XmlElement(name = "experience")
    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getDescription() {
        return description;
    }
    @XmlTransient
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }
    @XmlTransient
    public void setImage(String image) {
        this.image = image;
    }

    public User getParentid() {
        return parentid;
    }

    @XmlTransient
    public void setParentid(User parentid) {
        this.parentid = parentid;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", parentid=" + parentid +
                ", height=" + height +
                ", experience=" + experience +
                ", description='" + description + '\'' +
                '}';
    }
}
