package ap.entity;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workoutContainer")
@XmlRootElement
public class WorkoutContainer implements Serializable{

    private int WorkoutContainerId;
    private List<Workout> workoutList= new ArrayList<>();

    public WorkoutContainer() {

    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    public int getWorkoutContainerId() {

        return this.WorkoutContainerId;
    }

    @XmlTransient
    public void setWorkoutContainerId(int workoutContainerId) {
        this.WorkoutContainerId = workoutContainerId;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "workoutContainer", cascade = CascadeType.ALL)
    public List<Workout> getWorkoutList() {
        return workoutList;
    }


    @XmlElement(name = "workout")
    public void setWorkoutList(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }
}
