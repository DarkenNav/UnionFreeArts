package ru.unionfreearts.webservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "name", length = 248, nullable = false, unique = true)
    private String name;
    @JsonIgnore
    @OneToMany(targetEntity = Keyword.class, mappedBy = "person")
    private Set<Keyword> keywords;
    @JsonIgnore
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

    public Set<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<Keyword> keywords) {
        this.keywords = keywords;
    }

    public Set<Rank> getRanks() {
        return ranks;
    }

    public void setRanks(Set<Rank> ranks) {
        this.ranks = ranks;
    }
}
