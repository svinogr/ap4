package ap.dao.daoimpl;

import ap.dao.WorkoutDAO;
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
        workout.setName(nameOfNewWorkout);
        workout.setPosition(positionNewWorkout);
        workout.setParentid(user);
        add(workout);
          }

    @Override
    @Transactional
    public List<Workout> getListWorkout(int quantuty) throws HibernateException {
        List<Workout> list = new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Workout.class);
        criteria.addOrder(Order.asc("rate"));
        criteria.setMaxResults(quantuty);
        list=criteria.list();
        return list;
    }
}
