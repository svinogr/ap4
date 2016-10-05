package ap.entity;

import javax.persistence.*;

@Entity
@Table(name = "users_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "login", nullable = false)
    private String userName;

    @Column(name = "role_name", nullable = false)
    private  String roleName;

    public UserRole() {
    }

    public UserRole(String userName, Role role) {
        this.userName = userName;
        this.roleName = role.toString();
    }
}
