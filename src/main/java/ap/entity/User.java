package ap.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@XmlRootElement
@Entity
@Table(name = "users")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "login", unique = true)
    @NotEmpty(message = "Пожалуйста, введите логин")
    @Size(min = 3, message = "Размер логина должен быть не менее 3 символов")
    private String login;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Пожалуйста, введите пароль")
    @Size(min = 4, message = "Размер пароля должен быть минимум 4 символов")
    private String password;

    @Column(name = "email", unique = true)
    @Email(message = "Введенный адрес не является корректным адресом почты")
    @NotEmpty(message = "Пожалуйста, введите почтовый адрес")
    private String email;

    @Column(name = "date_registration")
    private Date dateRegistration;

    @Transient
    private UserDetails userDetails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentid", cascade = CascadeType.ALL)
    private List<Workout> workoutList = new ArrayList<>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentid", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>(0);

    @Column(name = "active")
    public boolean isActive() {
        return active;
    }

    @XmlTransient
    public void setActive(boolean active) {
        this.active = active;
    }

    private boolean active = false;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "parentid", cascade = CascadeType.ALL)
    private UserInfo userInfo;


    public List<Post> getPostList() {
        return postList;
    }

    @XmlTransient
    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }


    public UserInfo getUserInfo() {
        return userInfo;
    }

    @XmlTransient
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }


    public List<Workout> getWorkoutList() {
        return workoutList;
    }

    @XmlElement(name = "workout")
    public void setWorkoutList(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userDetails.getUsername();
    }//здесь что то

    @Override
    public boolean isAccountNonExpired() {
        return userDetails.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userDetails.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userDetails.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return userDetails.isEnabled();
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    @XmlTransient
    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }


    public int getId() {
        return id;
    }

    @XmlElement(name = "userid")
    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    @XmlTransient
    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    @XmlTransient
    public void setLogin(String login) {
        this.login = login;
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }

    @XmlTransient
    public void setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
    }
}
