package ru.unionfreearts.webapp.specifications.hibernate;

import ru.unionfreearts.webapp.entity.Person;
import ru.unionfreearts.webapp.specifications.Specification;
import ru.unionfreearts.webapp.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HSAllPersons implements Specification<Person> {

    public List<Person> toList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> rank = criteriaQuery.from(Person.class);
        criteriaQuery.select(rank);
        TypedQuery<Person> typedQuery = session.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }
}
