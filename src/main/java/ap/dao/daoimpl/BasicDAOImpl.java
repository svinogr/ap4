package ap.dao.daoimpl;

import ap.dao.BasicDAO;
import ap.entity.WorkoutContainer;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Type;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class BasicDAOImpl<T> implements BasicDAO<T> {
    private Class<T> type;

    public BasicDAOImpl(Class<T> type) {
        this.type = type;
    }

    public BasicDAOImpl() {
    }

    @Autowired
    SessionFactory sessionfactory;

    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    @Transactional
    public void add(T object) {
        try {
            Session session = sessionfactory.getCurrentSession();
            session.save(object);
        } catch (HibernateException e) {
        }

    }

    @Override
    public void delete(T object) {

    }

    @Override
    public void update(T object) {

    }




}
