package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.entity.*;

public class RepositoryFactory {
    private static final HibernateRepository<Site> siteRepository = new HibernateRepository<Site>(Site.class);
    private static final HibernateRepository<Person> personRepository = new HibernateRepository<Person>(Person.class);
    private static final HibernateRepository<Rank> rankRepository = new HibernateRepository<Rank>(Rank.class);
    private static final HibernateRepository<Keyword> keywordRepository = new HibernateRepository<Keyword>(Keyword.class);

    public static HibernateRepository<Site> getSiteRepository() {
        return siteRepository;
    }

    public static HibernateRepository<Person> getPersonRepository() {
        return personRepository;
    }

    public static HibernateRepository<Rank> getRankRepository() {
        return rankRepository;
    }

    public static HibernateRepository<Keyword> getKeywordRepository() {
        return keywordRepository;
    }
}
