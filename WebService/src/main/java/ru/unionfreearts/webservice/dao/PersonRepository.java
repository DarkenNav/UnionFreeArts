package ru.unionfreearts.webservice.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.unionfreearts.webservice.model.Person;

@Repository(value = "personRepository")
public class PersonRepository extends AbstractRepository<Person> {
    @Override
    public Person get(long id) {
        Person person;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            person = session.get(Person.class, id);
            session.getTransaction().commit();
            return person;
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }
}
