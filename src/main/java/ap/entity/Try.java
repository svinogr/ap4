package ap.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name = "tries")
public class Try implements Serializable {

    private int id;
    private int position;
    private double weight;
    private int repeat;
    private boolean done;
    private Exercise parentid;

    public Try(int id, int position, double weight, int repeat, boolean done) {
        this.id = id;
        this.position = position;
        this.weight = weight;
        this.repeat = repeat;
        this.done = done;
    }
    @Column(name = "done")
    public boolean isDone() {
        return done;
    }
    @XmlElement(name = "done")
    public void setDone(boolean done) {
        this.done = done;
    }

    public Try() {
    }

    public Try(Try tries) {
        this.id = tries.getId();
        this.position = tries.getPosition();
        this.weight = tries.getWeight();
        this.repeat = tries.getRepeat();
        this.parentid = tries.getParentid();
        this.done = tries.isDone();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    public Exercise getParentid() {
        return parentid;
    }

    @XmlTransient
    public void setParentid(Exercise parentid) {
        this.parentid = parentid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    public int getId() {
        return id;
    }

    @XmlElement(name = "tryId")
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "position")
    public int getPosition() {
        return this.position;
    }

    @XmlAttribute
    public void setPosition(int position) {
        this.position = position;
    }

    @Column(name = "weight")
    public double getWeight() {
        return this.weight;
    }

    @XmlElement
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Column(name = "repeat1")
    public int getRepeat() {
        return repeat;
    }

    @XmlElement
    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }
}
