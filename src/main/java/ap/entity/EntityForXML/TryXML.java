package ap.entity.EntityForXML;

import ap.entity.Try;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tryXML")
public class TryXML {
    private int exerciseId;
    private int tryId;
    private double weight;
    private int repeat;
    private boolean done;

    public TryXML() {
    }

    public TryXML(Try tries) {
        this.exerciseId = tries.getParentid().getId();
        this.tryId = tries.getId();
        this.weight = tries.getWeight();
        this.repeat = tries.getRepeat();
        this.done = tries.isDone();
    }

    public int getTryId() {
        return tryId;
    }

    @XmlElement(name = "tryId")
    public void setTryId(int tryId) {
        this.tryId = tryId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    @XmlElement(name = "exerciseId")
    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public double getWeight() {
        return weight;
    }

    @XmlElement(name = "weight")
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getRepeat() {
        return repeat;
    }

    @XmlElement(name = "repeat")
    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public boolean isDone() {
        return done;
    }

    @XmlElement(name = "done")
    public void setDone(boolean done) {
        this.done = done;
    }
}
