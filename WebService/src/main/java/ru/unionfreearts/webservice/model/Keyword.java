package ru.unionfreearts.webservice.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "keywords")
public class Keyword implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;
    @Column(name = "Name")
    private String name;
    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "PersonId")
    private Person person;

    public Keyword() {
    }

    public Keyword(Long id, String name, Person person) {
        this.id = id;
        this.name = name;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
