package ru.unionfreearts.webservice.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.unionfreearts.webservice.model.Page;

@Repository(value = "pageRepository")
public class PageRepository extends AbstractRepository<Page> {
    @Override
    public Page get(long id) {
        Page page;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            page = session.get(Page.class, id);
            session.getTransaction().commit();
            return page;
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }
}
