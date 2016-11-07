package ap.dao;

import ap.entity.User;
import ap.entity.Workout;

import java.util.List;

public interface WorkoutDAO extends BasicDAO<Workout>{
    public void createNewWorkout(String nameOfNewWorkout, int positionNewWorkout, User user);
    public List<Workout> getListWorkout(int quantuty);
}
