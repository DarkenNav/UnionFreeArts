package ru.unionfreearts.webservice.dao.fake;

import ru.unionfreearts.webservice.dao.IRepository;
import ru.unionfreearts.webservice.dao.specifications.Specification;
import ru.unionfreearts.webservice.model.Person;

import java.util.ArrayList;
import java.util.List;

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
