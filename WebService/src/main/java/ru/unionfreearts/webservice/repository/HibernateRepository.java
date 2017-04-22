package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.repository.specifications.Specification;
import ru.unionfreearts.webservice.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class HibernateRepository<T> implements IRepository<T> {
    private Class<T> tClass;

    public HibernateRepository(Class<T> tClass) {
        this.tClass = tClass;
    }

    public T get(long id) {
        T entity;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            entity = session.get(tClass, id);
            session.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    public boolean add(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
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

    public boolean set(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
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

    public boolean remove(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
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

    public List query(Specification specification) {
        return specification.toList();
    }
}
