package ap.entity.EntityForXML;

import ap.entity.Exercise;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class ExerciseXML extends Exercise {
    private int workoutId;
    private List<TryXML> list = new ArrayList<>(0);

    public ExerciseXML(Exercise exercise) {
        super(exercise);
        this.workoutId = exercise.getParentid().getWorkoutId();
    }

    public ExerciseXML() {
    }

    public List<TryXML> getList() {
        return list;
    }
    @XmlElement(name = "try")
    public void setList(List<TryXML> list) {
        this.list = list;
    }

    public int getWorkoutId() {
        return workoutId;
    }
    @XmlElement(name = "workoutId")
    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }
}
