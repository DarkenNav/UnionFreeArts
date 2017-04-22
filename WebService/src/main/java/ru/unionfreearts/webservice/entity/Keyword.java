package ru.unionfreearts.webservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "keywords")
public class Keyword implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "name", length = 248, nullable = false, unique = true)
    private String name;
    @JsonIgnore
    @ManyToOne(targetEntity = Person.class)
    private Person person;

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
