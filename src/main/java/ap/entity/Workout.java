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
public class Workout implements Serializable {
    int workoutId;
    int position;
    String name;
    List<Exercise> list = new ArrayList<>(0);
    WorkoutContainer workoutContainer;

    public Workout() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    public int getWorkoutId() {
        return workoutId;
    }

    @XmlTransient
    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }


    @Column(name = "position")
    public int getPosition() {
        return position;
    }

    @XmlAttribute
    public void setPosition(int position) {
        this.position = position;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workoutContainer_ID")
    public WorkoutContainer getWorkoutContainer() {
        return this.workoutContainer;
    }

    @XmlTransient
    public void setWorkoutContainer(WorkoutContainer workoutContainer) {
        this.workoutContainer = workoutContainer;
    }


    @Transient
   // @OneToMany(fetch = FetchType.LAZY, mappedBy = "workoutID")
    public List<Exercise> getList() {return this.list;
    }

    @XmlElement(name = "exercise")
    public void setList(List<Exercise> list) {
        this.list = list;
    }


    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }
}
