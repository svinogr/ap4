package ap.entity;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "image")
    private String image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentid", nullable = false)
    private Post parentid;

    public Images() {
    }

    public Images(String image, Post parentId) {
        this.image = image;
        this.parentid = parentId;
    }

    public Imagable getParentId() {
        return parentid;
    }

    public void setParentId(Post parentId) {
        this.parentid = parentId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
