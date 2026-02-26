package BT4.business;

import java.util.List;
import java.util.Optional;

public interface IBaseService<T> {
    void add(T t);
    void update(T t);
    void delete(T t);
    Optional<T> findByName(String name);
    List<T> findAll();
}
