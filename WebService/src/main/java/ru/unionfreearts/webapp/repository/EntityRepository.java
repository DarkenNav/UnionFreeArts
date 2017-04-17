package ru.unionfreearts.webapp.repository;

import ru.unionfreearts.webapp.specifications.Specification;
import ru.unionfreearts.webapp.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class EntityRepository<T> implements Repository<T> {
    private Class<T> tClass;

    public EntityRepository(Class<T> tClass) {
        this.tClass = tClass;
    }

    public T get(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        T entity = session.get(tClass, id);
        session.getTransaction().commit();
        session.close();
        return entity;
    }

    public void add(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void set(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void remove(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(entity);
        session.getTransaction().commit();
        session.close();
    }

    public List query(Specification specification) {
        return specification.toList();
    }


}
