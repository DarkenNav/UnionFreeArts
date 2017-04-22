package ru.unionfreearts.webservice.repository.fake;

import ru.unionfreearts.webservice.entity.Keyword;
import ru.unionfreearts.webservice.entity.Person;
import ru.unionfreearts.webservice.repository.Repository;
import ru.unionfreearts.webservice.specifications.Specification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FakePersons implements Repository<Person> {
    @Override
    public Person get(long id) {
        Person person = new Person("Путин");
        person.setId(id);
        person.setRanks(new HashSet<>());
        Set<Keyword> keywords = new HashSet<>();
        Keyword keyword1 = new Keyword();
        keyword1.setPerson(person);
        keyword1.setId(1L);
        keyword1.setName("Рутина");
        Keyword keyword2 = new Keyword();
        keyword2.setPerson(person);
        keyword2.setId(1L);
        keyword2.setName("Рутиным");
        keywords.add(keyword1);
        keywords.add(keyword2);
        person.setKeywords(keywords);
        return person;
    }

    @Override
    public boolean add(Person entity) {
        return true;
    }

    @Override
    public boolean set(Person entity) {
        return true;
    }

    @Override
    public boolean remove(Person entity) {
        return true;
    }

    @Override
    public List<Person> query(Specification specification) {
        Person person = new Person("Путин");
        person.setId(1L);
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(person);
        persons.add(person);
        return persons;
    }
}
