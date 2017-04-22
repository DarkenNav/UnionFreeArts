package ru.unionfreearts.webservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "person_page_rank")
public class Rank implements Serializable {
    @ManyToOne(targetEntity = Person.class)
    private Person person;
    @JsonIgnore
    @ManyToOne(targetEntity = Page.class)
    private Page page;
    @Column(name = "rank")
    private Integer rank;

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
}
