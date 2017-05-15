package ru.unionfreearts.webservice.dao.specifications.hibernate;

import ru.unionfreearts.webservice.dao.specifications.AbstractSpecification;
import ru.unionfreearts.webservice.model.Person;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AllPerson extends AbstractSpecification<Person> {

    @Override
    public Predicate toPredicate(Root<Person> root, CriteriaBuilder cb) {
        return cb.conjunction();
    }
}
