package ru.unionfreearts.webservice.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.unionfreearts.webservice.model.Site;

@Repository(value = "siteRepository")
public class SiteRepository extends AbstractRepository<Site> {
    @Override
    public Site get(long id) {
        Site site;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            site = session.get(Site.class, id);
            session.getTransaction().commit();
            return site;
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }
}
