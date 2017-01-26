package ap.entity.EntityForXML;

import ap.entity.Workout;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class WorkoutXML extends Workout {

    private int userId;

    private List<ExerciseXML> exerciseXMLList = new ArrayList<>(0);

    public WorkoutXML() {
    }

    public WorkoutXML(Workout workout) {
        super(workout);
        this.userId = workout.getParentid().getId();

    }

    public int getParentId() {
        return userId;
    }

    @XmlElement(name = "userId")
    public void setParentId(int parentId) {
        this.userId = parentId;
    }
    public List<ExerciseXML> getExerciseXMLList() {
        return exerciseXMLList;
    }
    @XmlElement(name = "exercise")
    public void setExerciseXMLList(List<ExerciseXML> exerciseXMLList) {
        this.exerciseXMLList = exerciseXMLList;
    }
}
