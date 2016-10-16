package ap.dao.daoimpl;

import ap.dao.WorkoutDAO;
import ap.entity.User;
import ap.entity.Workout;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class WorkoutDAOImpl extends BasicDAOImpl<Workout> implements WorkoutDAO {
    public WorkoutDAOImpl() {
       super(Workout.class);
    }

    @Override
    @Transactional
    public void createNewWorkout(String nameOfNewWorkout, int positionNewWorkout, User user) throws HibernateException {
        Workout workout = new Workout();
        workout.setName(nameOfNewWorkout);
        workout.setPosition(positionNewWorkout);
        workout.setParentid(user);
        add(workout);
          }
}
