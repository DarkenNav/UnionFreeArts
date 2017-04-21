package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.specifications.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Михалыч on 21.04.2017.
 */
public class FakeRepository<T> implements Repository<T> {
    private Class<T> tClass;
    private List<T> list;

    public FakeRepository(Class<T> tClass) {
        this.list = new ArrayList<T>();
        this.tClass = tClass;
    }

    public T get(long id) {
        return null;
    }

    public void add(T entity) {
        list.add(entity);
    }

    public void set(T entity) {

    }

    public void remove(T entity) {
        list.remove(entity);
    }

    public List<T> query(Specification specification) {
        return list;
    }
}
