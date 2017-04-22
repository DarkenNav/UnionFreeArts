package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.entity.*;

public class RepositoryFactory {
    private static final EntityRepository<Site> siteRepository = new EntityRepository<Site>(Site.class);
    private static final EntityRepository<Person> personRepository = new EntityRepository<Person>(Person.class);
    private static final EntityRepository<Rank> rankRepository = new EntityRepository<Rank>(Rank.class);
    private static final EntityRepository<Keyword> keywordRepository = new EntityRepository<Keyword>(Keyword.class);

    public static EntityRepository<Site> getSiteRepository() {
        return siteRepository;
    }

    public static EntityRepository<Person> getPersonRepository() {
        return personRepository;
    }

    public static EntityRepository<Rank> getRankRepository() {
        return rankRepository;
    }

    public static EntityRepository<Keyword> getKeywordRepository() {
        return keywordRepository;
    }
}
