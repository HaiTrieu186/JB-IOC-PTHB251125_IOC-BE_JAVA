package LT03.bussiness;

import java.util.List;

public interface IBaseService<T,ID> {
    void add(T t);
    void update(T t, ID id);
    void delete(ID id);
    T findByID(ID id);
    List<T> findAll();
    void sort();
}
