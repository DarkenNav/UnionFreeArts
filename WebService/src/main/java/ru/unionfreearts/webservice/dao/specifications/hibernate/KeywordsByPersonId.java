package ru.unionfreearts.webservice.dao.specifications.hibernate;

import ru.unionfreearts.webservice.dao.specifications.AbstractSpecification;
import ru.unionfreearts.webservice.model.Keyword;
import ru.unionfreearts.webservice.model.Keyword_;
import ru.unionfreearts.webservice.model.Person_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class KeywordsByPersonId extends AbstractSpecification<Keyword> {
    private long personId;

    public KeywordsByPersonId(long personId) {
        this.personId = personId;
    }

    @Override
    public Predicate toPredicate(Root<Keyword> root, CriteriaBuilder cb) {
        Path<Long> personIdPath = root.get(Keyword_.person).get(Person_.id);
        return cb.equal(personIdPath, personId);
    }
}
