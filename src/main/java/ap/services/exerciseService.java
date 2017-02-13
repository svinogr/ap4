package ap.services;

import ap.entity.EntityForXML.ExerciseXML;
import org.springframework.validation.BindingResult;

public interface ExerciseService {
    ExerciseXML createExercise(ExerciseXML exerciseXML);
    boolean changeExercise(ExerciseXML exerciseXML);
    boolean deleteExercise(int id);
    ExerciseXML getExerciseXML(int id);
    ExerciseXML validExerciseXML(ExerciseXML exerciseXML, BindingResult bindingResult);
}
