package ap.services;

import ap.entity.EntityForXML.UserXML;
import ap.entity.EntityForXML.WorkoutXML;
import ap.entity.Workout;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface WorkoutService {
    WorkoutXML createWorkout(WorkoutXML workoutXML);

    boolean changeWorkout(WorkoutXML workoutXML);

    boolean deleteWorkout(int id);

    WorkoutXML copyWorkout(int id);

    WorkoutXML rateWorkout(WorkoutXML workoutXML);

    WorkoutXML getWorkoutXML(int id);

    WorkoutXML validWorkoutXML(WorkoutXML workoutXML, BindingResult bindingResult);

    UserXML getAllWorkout(int start);

    UserXML getBestWorkout(int quantity);

    List<Workout> getAllWorkoutByUSerId(int id);

    int getQuantityWorkout();
}
