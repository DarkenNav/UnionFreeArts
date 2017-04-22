package ru.unionfreearts.webservice.repository.fake;

import ru.unionfreearts.webservice.entity.Keyword;
import ru.unionfreearts.webservice.entity.Person;
import ru.unionfreearts.webservice.repository.Repository;
import ru.unionfreearts.webservice.specifications.Specification;

import java.util.ArrayList;
import java.util.List;

public class FakeKeywords implements Repository<Keyword> {
    @Override
    public Keyword get(long id) {
        Keyword keyword = new Keyword();
        keyword.setId(id);
        keyword.setName("Путина");
        return keyword;
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
        Person person = new Person();
        person.setName("Путин");
        person.setId(1L);
        Keyword keyword = new Keyword();
        keyword.setId(1L);
        keyword.setName("Путина");
        keyword.setPerson(person);
        ArrayList<Keyword> keywords = new ArrayList<>();
        keywords.add(keyword);
        keywords.add(keyword);
        return keywords;
    }
}
