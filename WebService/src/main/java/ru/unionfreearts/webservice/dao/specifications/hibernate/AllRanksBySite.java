package ru.unionfreearts.webservice.dao.specifications.hibernate;

import ru.unionfreearts.webservice.dao.specifications.AbstractSpecification;
import ru.unionfreearts.webservice.model.Person;
import ru.unionfreearts.webservice.model.Rank;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AllRanks extends AbstractSpecification<Rank> {
    @Override
    public Predicate toPredicate(Root<Rank> root, CriteriaBuilder cb) {
        return cb.conjunction();
    }
}
