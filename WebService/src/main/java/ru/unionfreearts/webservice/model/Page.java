package ru.unionfreearts.webservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    @Temporal(TemporalType.DATE)
    @Column(name = "found_datetime")
    private Date foundDateTime;
    @Temporal(TemporalType.DATE)
    @Column(name = "last_scan_date")
    private Date lastScanDate;
    @JsonIgnore
    @OneToMany(targetEntity = Rank.class, mappedBy = "page")
    private List<Rank> ranks;

    public Page() {
    }

    public Page(Long id, String url, Site site, Date foundDateTime, Date lastScanDate) {
        this.id = id;
        this.url = url;
        this.site = site;
        this.foundDateTime = foundDateTime;
        this.lastScanDate = lastScanDate;
    }

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

    public Date getLastScanDate() {
        return lastScanDate;
    }

    public void setLastScanDate(Date lastScanDate) {
        this.lastScanDate = lastScanDate;
    }

    public List<Rank> getRanks() {
        return ranks;
    }

    public void setRanks(List<Rank> ranks) {
        this.ranks = ranks;
    }
}
