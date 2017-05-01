package ru.unionfreearts.webservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.unionfreearts.webservice.dao.specifications.Specification;
import ru.unionfreearts.webservice.util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.lang.annotation.Inherited;
import java.util.List;

@Transactional
public abstract class AbstractRepository<T> implements IRepository<T> {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public boolean add(T entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.save(entity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean set(T entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.update(entity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean remove(T entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.remove(entity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<T> query(Specification<T> specification) {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            // use specification.getType() to create a Root<T> instance
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(specification.getType());
            Root<T> root = criteriaQuery.from(specification.getType());

            // get predicate from specification
            Predicate predicate = specification.toPredicate(root, criteriaBuilder);

            // set predicate and execute query
            criteriaQuery.where(predicate);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }
}
