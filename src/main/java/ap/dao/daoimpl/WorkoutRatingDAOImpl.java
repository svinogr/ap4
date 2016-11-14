package ap.dao.daoimpl;

import ap.dao.WorkoutRatingDAO;
import ap.entity.WorkoutRating;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class WorkoutRatingDAOImpl extends BasicDAOImpl<WorkoutRating> implements WorkoutRatingDAO {

    @Autowired
    SessionFactory sessionFactory;


    /**
     * @param userId
     * @param workoutId
     * @return workoutRating
     */
    @Override
    @Transactional
    public WorkoutRating getRate(int userId, int workoutId) throws HibernateException {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(WorkoutRating.class);
        criteria.add(Restrictions.eq("userId", userId)).add(Restrictions.eq("workoutId", workoutId));
        WorkoutRating workoutRating;
        workoutRating = (WorkoutRating) criteria.uniqueResult();

        return workoutRating;
    }
}
