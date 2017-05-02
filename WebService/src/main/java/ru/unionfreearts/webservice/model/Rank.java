package ru.unionfreearts.webservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "person_page_rank")
public class Rank implements Serializable {
    @Id
    @ManyToOne(targetEntity = Person.class)
    private Person person;
    @Id
    @JsonIgnore
    @ManyToOne(targetEntity = Page.class)
    private Page page;
    @Column(name = "Rank")
    private Integer rank;

    public Rank() {
    }

    public Rank(Person person, Page page, Integer rank) {
        this.person = person;
        this.page = page;
        this.rank = rank;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rank rank = (Rank) o;

        if (!person.equals(rank.person)) return false;
        return page.equals(rank.page);
    }

    @Override
    public int hashCode() {
        int result = person.hashCode();
        result = 31 * result + page.hashCode();
        return result;
    }
}
