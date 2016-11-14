package ap.dao;

import ap.entity.User;
import ap.entity.Workout;

import java.util.List;

public interface WorkoutDAO extends BasicDAO<Workout>{
    void createNewWorkout(String nameOfNewWorkout, int positionNewWorkout, User user);
    List<Workout> getListWorkout(int quantuty);
}
