package LT02.business;

import java.util.List;

public interface IBaseService <T,ID> {
    void add(T t);
    void update(T t, ID id);
    void delete(ID id);
    T findByCode(ID id);
    List<T> findAll();
    void sort();
}
