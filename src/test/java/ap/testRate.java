package ap;

import ap.config.HibernateConfig;
import ap.dao.WorkoutDAO;
import ap.dao.WorkoutRatingDAO;
import ap.entity.User;
import ap.entity.Workout;
import ap.entity.WorkoutRating;
import ap.services.UserServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = HibernateConfig.class)
public class testRate {
    @Autowired
    UserServices userServices;
    @Autowired
    WorkoutDAO workoutDAO;
    @Autowired
    WorkoutRatingDAO workoutRatingDAO;



}
