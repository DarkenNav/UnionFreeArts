package ru.unionfreearts.webservice.dao.specifications.hibernate;

import ru.unionfreearts.webservice.dao.specifications.AbstractSpecification;
import ru.unionfreearts.webservice.model.Site;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AllSites extends AbstractSpecification<Site> {
    @Override
    public Predicate toPredicate(Root<Site> root, CriteriaBuilder cb) {
        return cb.conjunction();
    }
}
