package ap.entity;

import javax.persistence.*;

@Entity
@Table(name = "users_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "parantid", nullable = false)
    private int userId;

    @Column(name = "role_name", nullable = false)
    private  String roleName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLogin() {
        return userId;
    }

    public void setLogin(int userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public UserRole() {
    }

    public UserRole(int userId, Role role) {
        this.userId = userId;
        this.roleName = role.toString();
    }

}
