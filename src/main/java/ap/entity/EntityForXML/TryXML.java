package ap.entity.EntityForXML;

import ap.entity.Try;

import javax.xml.bind.annotation.XmlElement;

public class TryXML extends Try {
    private int exerciseId;

    public TryXML() {
    }

    public TryXML(Try tries) {
        super(tries);
        this.exerciseId = tries.getParentid().getId();
    }

    public int getExerciseId() {
        return exerciseId;
    }

    @XmlElement(name = "exerciseId")
    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }
}
