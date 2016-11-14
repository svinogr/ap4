package ap.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "workoutRating")
public class WorkoutRating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(name = "workoutId")
    int workoutId;

    @Column(name = "userId")
    int userId;

    @Column(name = "status")
    int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WorkoutRating{" +
                "id=" + id +
                ", workoutId=" + workoutId +
                ", userId=" + userId +
                ", status=" + status +
                '}';
    }
}
