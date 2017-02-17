package ap.entity.EntityForXML;

import ap.entity.Workout;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "workoutXML")
public class WorkoutXML {
    private int userId;
    private int workoutId;
    private String author;
    @NotNull
    @NotEmpty(message = "название не может быть пустым")
    @Size(max = 50,message = "слишком большое название")
    private String name;
    private int rate;
    private List<ExerciseXML> exerciseXMLList = new ArrayList<>(0);


    public WorkoutXML() {
    }

    public WorkoutXML(Workout workout) {
        this.userId = workout.getParentid().getId();
        this.workoutId = workout.getWorkoutId();
        this.author = workout.getAuthor();
        this.name = workout.getName();
        this.rate = workout.getRate();
    }

    public int getUserId() {
        return userId;
    }

    @XmlElement(name = "userId")
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWorkoutId() {
        return workoutId;
    }
    @XmlElement(name = "workoutId")
    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public String getAuthor() {
        return author;
    }
    @XmlElement(name = "author")
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }
    @XmlElement(name = "workoutName")
    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }
    @XmlElement(name = "rate")
    public void setRate(int rate) {
        this.rate = rate;
    }

    public List<ExerciseXML> getExerciseXMLList() {
        return exerciseXMLList;
    }
    @XmlElement(name = "exerciseXML")
    public void setExerciseXMLList(List<ExerciseXML> exerciseXMLList) {
        this.exerciseXMLList = exerciseXMLList;
    }
}
