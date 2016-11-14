package ap.dao.daoimpl;

import ap.dao.BasicDAO;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class BasicDAOImpl<T> implements BasicDAO<T> {

    @Autowired
    SessionFactory sessionFactory;

    private Class<T> type;

    public BasicDAOImpl(Class<T> type) {
        this.type = type;
    }

    public Class<T> getType() {
        return type;
    }

    public BasicDAOImpl() {
    }

    @Override
    @Transactional
    public List<T> getAll() {
        List<T> result = new ArrayList<>();
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(type);
            result.addAll(criteria.list());
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    @Transactional
    public List<T> getAllByParentKey(int id) {
        List<T> list = new ArrayList<>();
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(type);
            criteria.add(Restrictions.eq("parentid.id", id));
            list.addAll(criteria.list());
        } catch (HibernateException e) {
        }
        return list;
    }

    @Override
    @Transactional
    public void add(T object) throws HibernateException {
            Session session = sessionFactory.getCurrentSession();
            session.save(object);
    }

    @Override
    @Transactional
    public T getById(int id) throws HibernateException {
        Session session = sessionFactory.getCurrentSession();
        return session.load(type, id);
    }

    @Override
    @Transactional
    public void update(T object) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(object);
        } catch (HibernateException e) {
            System.out.println("не обновилось");}
    }


    @Override
    public void delete(T object)throws HibernateException {
            Session session = sessionFactory.getCurrentSession();
            session.delete(object);
    }
}
