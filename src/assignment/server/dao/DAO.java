package assignment.server.dao;

import java.util.List;

public interface DAO<T, K> {
    T create(T entity);
    T update(T entity);
    T getById(K id);
    void delete(K id);
    List<T> getAll();
}
