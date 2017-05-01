package ru.unionfreearts.webservice.dao.specifications.hibernate;

import org.hibernate.Session;
import ru.unionfreearts.webservice.dao.specifications.AbstractSpecification;
import ru.unionfreearts.webservice.model.Rank;
import ru.unionfreearts.webservice.model.Site;
import ru.unionfreearts.webservice.dao.specifications.Specification;
import ru.unionfreearts.webservice.util.HibernateUtil;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class HSAllSites extends AbstractSpecification<Site> {
    @Override
    public Predicate toPredicate(Root<Site> root, CriteriaBuilder cb) {
        return cb.conjunction();
    }
}
