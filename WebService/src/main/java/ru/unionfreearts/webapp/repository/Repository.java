package ru.unionfreearts.webapp.repository;

import ru.unionfreearts.webapp.specifications.Specification;

import java.util.List;

public interface Repository<T> {
    T get(long id);
    void add(T entity);
    void set(T entity);
    void remove(T entity);
    List<T> query(Specification specification);
}
