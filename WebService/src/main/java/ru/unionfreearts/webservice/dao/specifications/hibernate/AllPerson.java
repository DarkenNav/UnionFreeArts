package ru.unionfreearts.webservice.dao.specifications.hibernate;

import ru.unionfreearts.webservice.dao.specifications.AbstractSpecification;
import ru.unionfreearts.webservice.model.Person;
import ru.unionfreearts.webservice.dao.specifications.Specification;
import ru.unionfreearts.webservice.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class HSAllPersons extends AbstractSpecification<Person> {

    @Override
    public Predicate toPredicate(Root<Person> root, CriteriaBuilder cb) {
        return cb.conjunction();
    }
}
