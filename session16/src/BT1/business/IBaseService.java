package BT1.business;

import java.util.List;
import java.util.Optional;

public interface IBaseService <T,ID> {
    void add(T t);
    void update(T t);
    void delete(ID id);
    Optional<T> findById(ID id);
    List<T> findAll();
    void sort();
}
