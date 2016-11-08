package ap;

import ap.config.HibernateConfig;
import ap.dao.*;
import ap.entity.Exercise;
import ap.entity.Try;
import ap.entity.User;
import ap.entity.Workout;
import ap.services.UserServices;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.util.SerializationHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = HibernateConfig.class)
public class DaoTest {
    @Autowired
    BasicDAO basicDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    WorkoutDAO workoutDAO;
    @Autowired
    ExersiceDAO exersiceDAO;
    @Autowired
    TryDAO tryDAO;
    @Autowired
    UserServices userServices;
    @Autowired
    SessionFactory sessionFactory;


    @Test
    public void getAll() {
        List result = new ArrayList<>();
        // list Users
        result = userDAO.getAll();
        System.out.println(result.size());
        //list workout
        result = workoutDAO.getAll();
        System.out.println(result.size());
        //list exercise
        result = exersiceDAO.getAll();
        System.out.println(result.size());
        //lisr tries
        result = tryDAO.getAll();
        System.out.println(result.size());

    }

    @Test
    public void getAllByParentKey() {
        List result = new ArrayList<>();

        //list workout
        result = workoutDAO.getAll();
        System.out.println(result.size());
        //list exercise
        result = exersiceDAO.getAll();
        System.out.println(result.size());
        //lisr tries
        result = tryDAO.getAll();
        System.out.println(result.size());


    }

    @Test
    public void add() {
        User user = new User();
        user.setName("test");
        user.setLogin("test");
        user.setPassword("test");
        for (int i = 0; i < 4; i++) {
            Workout workout = new Workout();
            workout.setName("test" + i);
            workout.setParentid(user);
            for (int j = 0; j < 2; j++) {
                Exercise exercise = new Exercise();
                exercise.setName("test" + j);
                exercise.setPosition(j);
                exercise.setParentid(workout);
                workout.getExerciseList().add(exercise);
                for (int k = 0; k < 1; k++) {
                    Try tri = new Try();
                    tri.setPosition(k);
                    tri.setRepeat(10);
                    tri.setWeight(100);
                    tri.setParentid(exercise);
                    exercise.getTryList().add(tri);
                }
            }
            user.getWorkoutList().add(workout);
        }
        userServices.registrationUser(user);
    }


    @Test
    @Transactional
    public void getById() {
        User user = userDAO.getById(1);
        Assert.assertEquals("test1", user.getName());
        Workout workout = workoutDAO.getById(2);
        Assert.assertEquals("test1", workout.getName());
        Exercise exercise = exersiceDAO.getById(3);
        Assert.assertEquals("test1", exercise.getName());
        Try tri = tryDAO.getById(4);
        Assert.assertEquals(3, tri.getParentid().getId());
    }


    @Test
    @Transactional
    @Rollback(false)
    public void update() {
        User user = userDAO.getById(1);
        user.setEmail("new");
        userDAO.update(user);
       /* user = userDAO.getById(1);
        Assert.assertEquals("new",user.getEmail());*/
    }


    @Test
    public void delete() {
        User user = userDAO.getById(1);
        userDAO.delete(user);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void copyWorkout() {

        User userCopy = userServices.getById(130);

        EntityManagerFactory ent = Persistence.createEntityManagerFactory("");
        EntityManager ent2 = ent.createEntityManager();
        Workout workout1 = ent2.find(Workout.class,12);
        ent2.detach(workout1);
        workout1.setParentid(userCopy);
        workoutDAO.add(workout1);
        System.out.println(userCopy.getWorkoutList().size());

    }


}
