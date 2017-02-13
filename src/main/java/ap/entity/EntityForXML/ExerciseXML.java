package ap.entity.EntityForXML;

import ap.entity.Exercise;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class ExerciseXML  {
    private int workoutId;
    private int exerciseId;
    @NotNull
    @NotEmpty(message = "название не может быть пустым")
    @Size(max = 50,message = "слишком большое название")
    private String exerciseName;
    private int position;
    private List<TryXML> list = new ArrayList<>(0);

    public ExerciseXML() {
    }

    public ExerciseXML(Exercise exercise) {
        this.workoutId = exercise.getParentid().getWorkoutId();
        this.exerciseId = exercise.getId();
        this.exerciseName= exercise.getName();
        this.position = exercise.getPosition();
    }

    public int getWorkoutId() {
        return workoutId;
    }
    @XmlElement(name = "workoutId")
    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    @XmlElement(name = "exerciseId")
    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getName() {
        return exerciseName;
    }
    @XmlElement(name = "exerciseName")
    public void setName(String name) {
        this.exerciseName = name;
    }

    public List<TryXML> getList() {
        return list;
    }

    @XmlElement(name = "tryXML")
    public void setList(List<TryXML> list) {
        this.list = list;
    }


    public int getPosition() {
        return position;
    }
    @XmlElement(name = "position")
    public void setPosition(int position) {
        this.position = position;
    }
}
