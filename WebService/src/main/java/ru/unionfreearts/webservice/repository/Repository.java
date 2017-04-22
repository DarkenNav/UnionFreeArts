package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.specifications.Specification;

import java.util.List;

public interface Repository<T> {
    T get(long id);
    boolean add(T entity);
    boolean set(T entity);
    boolean remove(T entity);
    List<T> query(Specification specification);
}
