package ru.unionfreearts.webservice.dao.specifications;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;

public class AbstractSpecification<T> implements Specification<T> {
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaBuilder cb) {
        throw new NotImplementedException();
    }

    @Override
    public Class<T> getType() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[0];
    }
}
