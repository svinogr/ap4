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
public class Workout implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    protected int workoutId;

    @Column(name = "position")
    protected int position;

    @Column(name = "name")
    protected String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentid", cascade = CascadeType.ALL)
    protected List<Exercise> exerciseList = new ArrayList<>(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentid")
    protected User parentid;

    @Column(name = "rate")
    protected int rate = 0;

    @Column(name = "copy")
    protected boolean copy = false;

    public boolean isCopy() {
        return copy;
    }
    @XmlElement(name = "copy")
    public void setCopy(boolean copy) {
        this.copy = copy;
    }

    public int getRate() {
        return rate;
    }
    @XmlElement(name = "rate")
    public void setRate(int rate) {
        this.rate = rate;
    }

    @Column(name = "author")
    protected String author="";

    @Column(name = "description")
    protected String description;

    public String getDescription() {
        return description;
    }
    @XmlElement(name = "description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Workout() {
    }

    public Workout(Workout workout) {
        this.position = workout.getPosition();
        this.name = workout.getName();
        this.exerciseList = workout.getExerciseList();
        this.rate = workout.getRate();
        this.copy = workout.isCopy();
        this.author = workout.getAuthor();
        this.description = workout.getDescription();
        this.workoutId = workout.getWorkoutId();
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public String getAuthor() {
        return author;
    }

    @XmlElement(name = "author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @XmlElement(name = "workoutId")
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

    @XmlElement(name = "workoutName")
    public void setName(String name) {
        this.name = name;
    }


}
