package ru.unionfreearts.webservice.dao.fake;

import ru.unionfreearts.webservice.model.Keyword;
import ru.unionfreearts.webservice.model.Person;
import ru.unionfreearts.webservice.dao.IRepository;
import ru.unionfreearts.webservice.dao.specifications.Specification;

import java.util.ArrayList;
import java.util.List;

public class FakeKeywords implements IRepository<Keyword> {
    @Override
    public Keyword get(long id) {
        return new Keyword(1L, "Путина", new Person(1L, "Путин"));
    }

    @Override
    public boolean add(Keyword entity) {
        return true;
    }

    @Override
    public boolean set(Keyword entity) {
        return true;
    }

    @Override
    public boolean remove(Keyword entity) {
        return true;
    }

    @Override
    public List<Keyword> query(Specification specification) {
        Keyword keyword = new Keyword(1L, "Путина", new Person(1L, "Путин"));
        ArrayList<Keyword> keywords = new ArrayList<>();
        keywords.add(keyword);
        keywords.add(keyword);
        return keywords;
    }
}
