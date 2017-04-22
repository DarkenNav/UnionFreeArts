package ru.unionfreearts.webservice.repository.specifications.hibernate;

import ru.unionfreearts.webservice.entity.Person;
import ru.unionfreearts.webservice.repository.specifications.Specification;
import ru.unionfreearts.webservice.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HSAllPersons implements Specification<Person> {

    public List<Person> toList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
            Root<Person> rank = criteriaQuery.from(Person.class);
            criteriaQuery.select(rank);
            TypedQuery<Person> typedQuery = session.createQuery(criteriaQuery);
            return typedQuery.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }
}
