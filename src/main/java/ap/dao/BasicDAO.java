package ap.dao;

import java.util.List;

public interface BasicDAO<T> {
    public List<T> getAll();
    public List<T> getAllByParentKey(int id);
    public void add(T object);
    public void delete(T object);
    public void update(T object);
    public T getById(int id);

}
