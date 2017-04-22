package ru.unionfreearts.webservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.unionfreearts.webservice.entity.Person;
import ru.unionfreearts.webservice.repository.Repository;
import ru.unionfreearts.webservice.repository.fake.FakePersons;
import ru.unionfreearts.webservice.specifications.hibernate.HSAllSites;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {
    private Repository<Person> personRepository = new FakePersons();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Person> get(@PathVariable Long id) {
        Person person = personRepository.get(id);
        if (person != null) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
        return new ResponseEntity<>(person, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Person>> getAll() {
        List<Person> personList = personRepository.query(new HSAllSites());
        if (personList != null) {
            return new ResponseEntity<>(personList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Person> add(@RequestBody Person person) {
        System.out.println(person.getId()+" "+person.getName());
        if (personRepository.add(person)) {
            return new ResponseEntity<>(person, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(person, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Person> update(@RequestBody Person person){
        if (personRepository.set(person)) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
        return new ResponseEntity<>(person, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Person> remove(@RequestBody Person person){
        if (personRepository.remove(person)) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
        return new ResponseEntity<>(person, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
