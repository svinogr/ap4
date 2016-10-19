package ap.dao;

import ap.entity.Exercise;
import ap.entity.Workout;

public interface ExersiceDAO extends BasicDAO<Exercise> {
    public void createNewExercise(String name, Workout workout);
}
