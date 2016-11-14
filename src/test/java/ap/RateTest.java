package ap;

import ap.config.HibernateConfig;
import ap.dao.WorkoutDAO;
import ap.dao.WorkoutRatingDAO;
import ap.entity.Workout;
import ap.entity.WorkoutRating;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = HibernateConfig.class)
public class RateTest {
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    WorkoutRatingDAO workoutRatingDAO;
    @Autowired
    WorkoutDAO workoutDAO;

    @Test
    public void opo() {
        int idWorkout = 3;
        int rate = 1;
        int userId = 2;
        System.err.println(idWorkout + " " + rate + " " + userId);
        WorkoutRating workoutRating;
        System.err.println(idWorkout + " " + rate + " " + userId);
        Workout workout;
        System.err.println(idWorkout + " " + rate + " " + userId);
        workoutRating = workoutRatingDAO.getRate(userId, idWorkout);
        System.out.println(workoutRating);
        workout = workoutDAO.getById(idWorkout);

        if (workoutRating != null) {
            int status = workoutRating.getStatus();
            System.err.println("deededededededed");
            if (status == 0 && rate == 0) {
                System.out.println("уже голосовал так");
            } else if (status == 0 && rate == 1) {
                workoutRating.setStatus(1);
                workout.setRate(workout.getRate() + 1);
            }
            if (status == 1 && rate == 1) {
                System.out.println("уже голосовал так");
            } else if (status == 1 && rate == 0) {
                workoutRating.setStatus(1);
                workout.setRate(workout.getRate() - 1);
            }

        } else {
            System.err.println("gggggggggggggggggggggggggggg");
            WorkoutRating newWorkoutRating = new WorkoutRating();
            newWorkoutRating.setWorkoutId(idWorkout);
            newWorkoutRating.setUserId(userId);
            newWorkoutRating.setStatus(rate);
            System.err.println(newWorkoutRating.toString());
            workoutRatingDAO.add(newWorkoutRating);

        }
    }
}
