/*
package ap.dao.daoimpl;

import ap.dao.WorkoutContainerDAO;
import ap.entity.WorkoutContainer;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class WorkoutContainerDAOImpl extends BasicDAOImpl implements WorkoutContainerDAO {


    @Override
  public WorkoutContainer getById(int id) {
     WorkoutContainer workoutContainer= new WorkoutContainer();
        workoutContainer.setWorkoutContainerId(450);
        try (Session session = sessionfactory.getCurrentSession()) {
        Criteria criteria = session.createCriteria(WorkoutContainer.class);
            criteria.add(Restrictions.eq("id", id));

           workoutContainer= session.g;
        } catch (HibernateException e) {
        }

        return workoutContainer
;
    }


*/
