package ru.unionfreearts.webservice.dao.specifications.hibernate;

import ru.unionfreearts.webservice.dao.specifications.AbstractSpecification;
import ru.unionfreearts.webservice.model.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AllRanksBySite extends AbstractSpecification<Rank> {
    private Long siteId;

    public AllRanksBySite(Long siteId) {
        this.siteId = siteId;
    }

    @Override
    public Predicate toPredicate(Root<Rank> root, CriteriaBuilder cb) {
        Path<Page> page = root.get(Rank_.page);
        Path<Site> site = page.get(Page_.site);
        Path<Long> id = site.get(Site_.id);
        return cb.equal(id, siteId);
    }
}
