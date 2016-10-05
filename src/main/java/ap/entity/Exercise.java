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
public class Exercise implements Serializable {

    int id;
    int position;
    String name;
    List<Try> tryList = new ArrayList<>(0);
    Workout workoutID;

    public Exercise() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    public int getId() {
        return id;
    }

    @XmlTransient
    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "position")
    public int getPosition() {
        return position;
    }

    @XmlAttribute
    public void setPosition(int position) {
        this.position = position;
    }


    @Column(name = "name")
    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id", nullable = false)
    public Workout getWorkoutID() {
        return this.workoutID;
    }

    @XmlTransient
    public void setWorkoutID(Workout workoutID) {
        this.workoutID = workoutID;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "exerciseId")
    public List<Try> getTryList() {
        return tryList;
    }

    @XmlElement(name = "tryes")
    public void setTryList(List<Try> tryList) {
        this.tryList = tryList;
    }
}
