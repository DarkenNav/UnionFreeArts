package ru.unionfreearts.webservice.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.unionfreearts.webservice.model.Keyword;

@Repository(value = "keywordRepository")
public class KeywordRepository extends AbstractRepository<Keyword> {

    @Override
    public Keyword get(long id) {
        Keyword keyword;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            keyword = session.get(Keyword.class, id);
            session.getTransaction().commit();
            return keyword;
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }
}
