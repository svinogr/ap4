package ap.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "post")
public class Post implements Serializable, Imagable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentid", nullable = false)
    private User parentid;

    @Column(name = "date")
    private Date date;
    @Column(name = "description")
    private String description;

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentid", cascade = CascadeType.ALL)
    private List<Images> image = new ArrayList<>(0);*/

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getParentId() {
        return parentid;
    }

    public void setParentId(User parentId) {
        this.parentid = parentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public List<Images> getImage() {
        return image;
    }

    public void setImage(List<Images> image) {
        this.image = image;
    }*/
}
