package mapper;

import java.util.List;

/**
 * Interface generik untuk ORM Data Mapper Pattern.
 * @param <T> Tipe model/entity
 */
public interface Mapper<T> {
    void save(T obj);
    T findById(String id);
    List<T> findAll();
    void update(T obj);
    void delete(String id);
}