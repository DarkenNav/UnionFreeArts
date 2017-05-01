package ru.unionfreearts.webservice.repository.fake;

import ru.unionfreearts.webservice.entity.Keyword;
import ru.unionfreearts.webservice.entity.Person;
import ru.unionfreearts.webservice.repository.IRepository;
import ru.unionfreearts.webservice.repository.specifications.Specification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FakePersons implements IRepository<Person> {
    @Override
    public Person get(long id) {
        Person person = new Person(id, "Путин");
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
        Person person = new Person(1L, "Путин");
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(person);
        persons.add(person);
        return persons;
    }
}
