package ap.dao.daoimpl;

import ap.dao.BasicDAO;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public int add(T object) throws HibernateException {
        Session session = sessionFactory.getCurrentSession();
        int savedid = (int) session.save(object);
        return savedid;
    }

    @Override
    @Transactional
    public T getById(int id) throws HibernateException {
        Session session = sessionFactory.getCurrentSession();
        return session.load(type, id);
    }

    @Override
    @Transactional
    public List<T> getSearchResultOneParameter(Map<String, String> map) {
        List<T> list = new ArrayList<>();
        String key = null;
        String value = null;
        for (Map.Entry<String, String> pair : map.entrySet()) {
            key = pair.getKey();
            value = pair.getValue();
        }
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(type);
            criteria.add(Restrictions.eq(key, value));
            list = criteria.list();
        } catch (HibernateException e) {
        }
        return list;
    }


    @Override
    @Transactional
    public void update(T object) throws HibernateException {

        Session session = sessionFactory.getCurrentSession();
        session.update(object);

    }


    @Override
    @Transactional
    public void delete(T object) throws HibernateException {
        Session session = sessionFactory.getCurrentSession();
        session.delete(object);
    }

    @Override
    public boolean checkItBD(int id) {
        boolean flag = false;
        List<T> list = new ArrayList<>(0);
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(type);
        criteria.add(Restrictions.eq("id", id));
        list.addAll(criteria.list());
        if (list.size() > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    @Transactional
    public int getQuantityRow() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria =  session.createCriteria(type);
        return Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
    }

}
