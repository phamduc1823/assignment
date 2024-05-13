package assignment.server.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T, K> {
    List<T> getAll() throws SQLException;
    T create(T entity) throws SQLException;
    T getById(K id);
    T update(T entity);
    T delete(K id);
}
