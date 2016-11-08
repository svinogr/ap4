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
@Table(name = "workout")
public class Workout implements Serializable, Xmlable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int workoutId;

    @Column(name = "position")
    private int position;

    @Column(name = "name")
    private String name;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentid", cascade = CascadeType.ALL)
    private List<Exercise> exerciseList = new ArrayList<>(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentid")
    private User parentid;

    @Column(name = "rate")
    private int rate = 0;

    public int getRate() {
        return rate;
    }
    @XmlElement(name = "rate")
    public void setRate(int rate) {
        this.rate = rate;
    }

    public Workout() {
    }


    public int getWorkoutId() {
        return workoutId;
    }

    @XmlElement(name = "id")
    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }


    public int getPosition() {
        return position;
    }

    @XmlAttribute
    public void setPosition(int position) {
        this.position = position;
    }


    public User getParentid() {
        return this.parentid;
    }

    @XmlTransient
    public void setParentid(User user) {
        this.parentid = user;
    }


    public List<Exercise> getExerciseList() {
        return this.exerciseList;
    }

    @XmlElement(name = "exercise")
    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }


    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }


}
