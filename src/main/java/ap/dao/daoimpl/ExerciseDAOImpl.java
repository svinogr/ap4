package ap.dao.daoimpl;

import ap.dao.ExersiceDAO;
import ap.entity.Exercise;
import ap.entity.Workout;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ExerciseDAOImpl extends BasicDAOImpl<Exercise> implements ExersiceDAO {
    public ExerciseDAOImpl() {super(Exercise.class);}

    @Override
    @Transactional
    public void createNewExercise(String name, Workout workout)throws HibernateException {
        Exercise exercise = new Exercise();
        exercise.setName(name);
        exercise.setParentid(workout);
        add(exercise);
    }
}
