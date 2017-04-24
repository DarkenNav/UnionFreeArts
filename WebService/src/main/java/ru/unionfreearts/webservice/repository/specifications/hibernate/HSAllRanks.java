package ru.unionfreearts.webservice.repository.specifications.hibernate;

import org.hibernate.Session;
import ru.unionfreearts.webservice.entity.Person;
import ru.unionfreearts.webservice.entity.Rank;
import ru.unionfreearts.webservice.repository.specifications.Specification;
import ru.unionfreearts.webservice.util.HibernateUtil;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HSAllRanks implements Specification<Rank> {
    @Override
    public List<Rank> toList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Rank> criteriaQuery = criteriaBuilder.createQuery(Rank.class);
            Root<Rank> rank = criteriaQuery.from(Rank.class);
            criteriaQuery.select(rank);
            TypedQuery<Rank> typedQuery = session.createQuery(criteriaQuery);
            return typedQuery.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }
}
