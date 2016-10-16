package ap.dao;

import ap.entity.User;
import ap.entity.Workout;

public interface WorkoutDAO extends BasicDAO<Workout>{
    public void createNewWorkout(String nameOfNewWorkout, int positionNewWorkout, User user);
}
