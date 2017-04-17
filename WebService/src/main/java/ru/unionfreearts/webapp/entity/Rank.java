package ru.unionfreearts.webapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "person_page_rank")
public class Rank {
    @ManyToOne(targetEntity = Person.class)
    private Person person;
    @ManyToOne(targetEntity = Page.class)
    private Page page;
    @Id
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
