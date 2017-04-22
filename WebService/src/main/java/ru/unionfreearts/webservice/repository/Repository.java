package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.specifications.Specification;

import java.util.List;

public interface Repository<T> {
    T get(long id);
    void add(T entity);
    void set(T entity);
    void remove(T entity);
    List<T> query(Specification specification);
}
