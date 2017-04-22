package ru.unionfreearts.webservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "pages")
public class Page implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "url", length = 248, nullable = false, unique = true)
    private String url;
    @ManyToOne(targetEntity = Site.class)
    private Site site;
    @Column(name = "found_datetime")
    private Date foundDateTime;
    @Column(name = "last_datetime")
    private Date lastDateTime;
    @JsonIgnore
    @OneToMany(targetEntity = Rank.class, mappedBy = "page")
    private Set<Rank> ranks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Date getFoundDateTime() {
        return foundDateTime;
    }

    public void setFoundDateTime(Date foundDateTime) {
        this.foundDateTime = foundDateTime;
    }

    public Date getLastDateTime() {
        return lastDateTime;
    }

    public void setLastDateTime(Date lastDateTime) {
        this.lastDateTime = lastDateTime;
    }

    public Set<Rank> getRanks() {
        return ranks;
    }

    public void setRanks(Set<Rank> ranks) {
        this.ranks = ranks;
    }
}
