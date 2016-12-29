package ap.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Entity
@Table(name = "exercise")
public class Exercise implements Serializable, Xmlable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "position")
    private int position;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentid", cascade = CascadeType.ALL)
    private List<Try> tryList = new ArrayList<>(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentid", nullable = false)
    private Workout parentid;

    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }
    @XmlElement(name = "description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Exercise() {
    }

    public int getId() {
        return id;
    }

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    @XmlAttribute
    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public Workout getParentid() {
        return this.parentid;
    }

    @XmlTransient
    public void setParentid(Workout parentid) {
        this.parentid = parentid;
    }


    public List<Try> getTryList() {
        return tryList;
    }

    @XmlElement(name = "tryes")
    public void setTryList(List<Try> tryList) {
        this.tryList = tryList;
    }
}
