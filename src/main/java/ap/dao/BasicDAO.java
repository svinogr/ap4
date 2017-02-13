package ap.dao;

import org.hibernate.HibernateException;

import java.util.List;
import java.util.Map;

public interface BasicDAO<T> {
     List<T> getAll();
     List<T> getAllByParentKey(int id);
      int add(T object) throws HibernateException;
     void delete(T object);
     void update(T object);
     T getById(int id) throws HibernateException;
     List<T> getSearchResultOneParameter(Map<String, String> map);
     boolean checkItBD(int id);


}
