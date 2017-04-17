package ru.unionfreearts.webservice.specifications.hibernate;

import org.hibernate.Session;
import ru.unionfreearts.webservice.entity.Site;
import ru.unionfreearts.webservice.specifications.Specification;
import ru.unionfreearts.webservice.util.HibernateUtil;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HSAllSites implements Specification<Site> {
    public List<Site> toList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Site> criteriaQuery = criteriaBuilder.createQuery(Site.class);
        Root<Site> rank = criteriaQuery.from(Site.class);
        criteriaQuery.select(rank);
        TypedQuery<Site> typedQuery = session.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
}
