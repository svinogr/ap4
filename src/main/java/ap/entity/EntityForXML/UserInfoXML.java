package ap.entity.EntityForXML;

import ap.entity.UserInfo;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "userInfo")
public class UserInfoXML{
    private int userId;
    private int userInfoId;
    private String login;
    @Size(max = 20, message ="слишком большой текст")
    private String name;
    @Size(max = 200, message ="слишком большой текст")
    private String description;
    private int age;
    private int height;
    private int weight;
    private String linkImage;

    public UserInfoXML() {
    }

    public UserInfoXML(UserInfo userInfo) {
        this.userId = userInfo.getParentid().getId();
        this.userInfoId = userInfo.getId();
        this.login = userInfo.getLogin();
        this.name  = userInfo.getName();
        this.age = userInfo.getAge();
        this.height = userInfo.getHeight();
        this.weight = userInfo.getWeight();
        this.description = userInfo.getDescription();
        this.linkImage = "link";
    }

    public UserInfoXML(UserInfo userInfo, String linkImage) {
        this.userId = userInfo.getParentid().getId();
        this.userInfoId = userInfo.getId();
        this.login = userInfo.getLogin();
        this.name  = userInfo.getName();
        this.age = userInfo.getAge();
        this.height = userInfo.getHeight();
        this.weight = userInfo.getWeight();
        this.description = userInfo.getDescription();
        this.linkImage = linkImage;
    }



    public int getUserId() {
        return userId;
    }

    @XmlElement(name = "userId")
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserInfoId() {
        return userInfoId;
    }
    @XmlElement(name = "userInfoId")
    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getName() {
        return name;
    }
    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }
    @XmlElement(name = "login")
    public void setLogin(String login) {
        this.login = login;
    }

    public int getAge() {
        return age;
    }
    @XmlElement(name = "age")
    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }
    @XmlElement(name = "height")
    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }
    @XmlElement(name = "weight")
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getLinkImage() {
        return linkImage;
    }
    @XmlElement(name = "linkImage")
    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public String getDescription() {
        return description;
    }
    @XmlElement(name = "description")
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserInfoXML{" +
                "userId=" + userId +
                ", userInfoId=" + userInfoId +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", linkImage='" + linkImage + '\'' +
                '}';
    }
}
