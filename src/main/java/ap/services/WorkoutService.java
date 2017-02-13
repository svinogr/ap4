package ap.services;

import ap.entity.EntityForXML.UserXML;
import ap.entity.EntityForXML.WorkoutXML;
import org.springframework.validation.BindingResult;

public interface WorkoutService {
    WorkoutXML createWorkout(WorkoutXML workoutXML);

    boolean changeWorkout(WorkoutXML workoutXML);

    boolean deleteWorkout(int id);

    WorkoutXML copyWorkout(int id);

    WorkoutXML rateWorkout(WorkoutXML workoutXML);

    WorkoutXML getWorkoutXML(int id);

    WorkoutXML validWorkoutXML(WorkoutXML workoutXML, BindingResult bindingResult);

    UserXML getAllWorkout(int start);
}
