package ap.dao.daoimpl;

import ap.dao.WorkoutDAO;
import ap.entity.Exercise;
import ap.entity.Try;
import ap.entity.User;
import ap.entity.Workout;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorkoutDAOImpl extends BasicDAOImpl<Workout> implements WorkoutDAO {
    @Autowired
    SessionFactory sessionFactory;

    public WorkoutDAOImpl() {
        super(Workout.class);
    }

    @Override
    @Transactional
    public void createNewWorkout(String nameOfNewWorkout, int positionNewWorkout, User user) throws HibernateException {
        Workout workout = new Workout();
        workout.setName(nameOfNewWorkout.toLowerCase());
        workout.setPosition(positionNewWorkout);
        String author= user.getUserInfo().getName();
        workout.setAuthor(author);
        workout.setParentid(user);
        add(workout);
    }

    @Override
    @Transactional
    public List<Workout> getListWorkout(int quantity) throws HibernateException {
        List<Workout> list;
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Workout.class);
        criteria.addOrder(Order.desc("rate"));
        criteria.add(Restrictions.eq("copy",false));
        criteria.setMaxResults(quantity);
        list = criteria.list();
        return list;
    }

    @Override
    public Workout copyWorkout(int idWorkout, User user )throws HibernateException  {
        Workout workout = this.getById(idWorkout);
        List<Exercise> listExercise = new ArrayList<>();
        listExercise.addAll(workout.getExerciseList());
        Workout newWorkout = new Workout();
        newWorkout.setRate(0);
        newWorkout.setPosition(0);
        newWorkout.setName(workout.getName());
        newWorkout.setCopy(true);
        newWorkout.setAuthor(workout.getAuthor());
        if (listExercise.size() != 0) {
            for (int i = 0; i < workout.getExerciseList().size(); i++) {
                if (workout.getExerciseList().get(i).getTryList().size() != 0) {
                    Exercise newExercise = new Exercise();
                    newExercise.setPosition(0);
                    newExercise.setName(listExercise.get(i).getName());
                    for (int j = 0; j < listExercise.get(i).getTryList().size(); j++) {
                        Try tries = new Try();
                        tries.setWeight(listExercise.get(i).getTryList().get(j).getWeight());
                        tries.setRepeat(listExercise.get(i).getTryList().get(j).getRepeat());
                        tries.setParentid(newExercise);
                        newExercise.getTryList().add(tries);
                    }
                    newExercise.setParentid(newWorkout);
                    newWorkout.getExerciseList().add(newExercise);
                }
            }
            newWorkout.setParentid(user);
        }
        newWorkout.setParentid(user);
        this.add(newWorkout);
        return newWorkout;
    }

    @Override
    @Transactional
    public List<Workout> getListAllWorkout(int start) {
        List<Workout> list = new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Workout.class);
        criteria.addOrder(Order.desc("rate"));
        criteria.setMaxResults(20);
        criteria.setFirstResult(start);
        list.addAll(criteria.list());
        return list;
    }

}

