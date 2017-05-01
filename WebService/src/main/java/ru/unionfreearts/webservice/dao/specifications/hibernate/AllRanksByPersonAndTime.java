package ru.unionfreearts.webservice.dao.specifications.hibernate;

import ru.unionfreearts.webservice.dao.specifications.AbstractSpecification;
import ru.unionfreearts.webservice.model.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class AllRanksByPersonAndTime extends AbstractSpecification<Rank> {
    private Long personId;
    private Long siteId;
    private Date startDate;
    private Date finishDate;

    public AllRanksByPersonAndTime(Long personId, Long siteId, Date startDate, Date finishDate) {
        this.personId = personId;
        this.siteId = siteId;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    @Override
    public Predicate toPredicate(Root<Rank> root, CriteriaBuilder cb) {
        Path<Long> personIdPath = root.get(Rank_.person).get(Person_.id);
        Path<Long> siteIdPath = root.get(Rank_.page).get(Page_.site).get(Site_.id);
        Path<Date> datePath = root.get(Rank_.page).get(Page_.foundDateTime);
        return cb.and(cb.equal(personIdPath, personId),
                      cb.equal(siteIdPath, siteId),
                      cb.between(datePath, startDate, finishDate));
    }
}
