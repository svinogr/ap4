package ap.entity;


import javax.persistence.*;

@Entity
@Table(name = "workoutRating")
public class WorkoutRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(name = "workoutId")
    int workoutId;

    @Column(name = "userId")
    int userId;

    @Column(name = "ratePlus")
    boolean ratePlus;

    @Column(name = "rateMinus")
    boolean rateMinus;

    public WorkoutRating() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isRatePlus() {
        return ratePlus;
    }

    public void setRatePlus(boolean ratePlus) {
        this.ratePlus = ratePlus;
    }

    public boolean isRateMinus() {
        return rateMinus;
    }

    public void setRateMinus(boolean rateMinus) {
        this.rateMinus = rateMinus;
    }
}
