package ru.unionfreearts.webservice.dao;

import ru.unionfreearts.webservice.dao.specifications.Specification;

import java.util.List;

public interface IRepository<T> {
    T get(long id);
    boolean add(T entity);
    boolean set(T entity);
    boolean remove(T entity);
    List<T> query(Specification<T> specification);
}
