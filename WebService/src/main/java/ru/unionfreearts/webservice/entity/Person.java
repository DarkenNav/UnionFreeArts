package ru.unionfreearts.webservice.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "name", length = 248, nullable = false, unique = true)
    private String name;
    @OneToMany(targetEntity = Rank.class, mappedBy = "person")
    private Set<Rank> ranks;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
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

    public Set<Rank> getRanks() {
        return ranks;
    }

    public void setRanks(Set<Rank> ranks) {
        this.ranks = ranks;
    }
}
