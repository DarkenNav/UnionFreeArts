package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.entity.*;
import ru.unionfreearts.webservice.repository.fake.FakeKeywords;
import ru.unionfreearts.webservice.repository.fake.FakePersons;
import ru.unionfreearts.webservice.repository.fake.FakeRanks;
import ru.unionfreearts.webservice.repository.fake.FakeSites;

public class Repositories {
    private static final HibernateRepository<Site> SITE_HIBERNATE_REPOSITORY;
    private static final HibernateRepository<Person> PERSON_HIBERNATE_REPOSITORY;
    private static final HibernateRepository<Rank> RANK_HIBERNATE_REPOSITORY;
    private static final HibernateRepository<Keyword> KEYWORD_HIBERNATE_REPOSITORY;
    private static final FakeSites SITE_FAKE_REPOSITORY;
    private static final FakeKeywords KEYWORD_FAKE_REPOSITORY;
    private static final FakePersons PERSON_FAKE_REPOSITORY;
    private static final FakeRanks RANK_FAKE_REPOSITORY;


    static {
        SITE_HIBERNATE_REPOSITORY = new HibernateRepository<>(Site.class);
        PERSON_HIBERNATE_REPOSITORY = new HibernateRepository<>(Person.class);
        RANK_HIBERNATE_REPOSITORY = new HibernateRepository<>(Rank.class);
        KEYWORD_HIBERNATE_REPOSITORY = new HibernateRepository<>(Keyword.class);
        SITE_FAKE_REPOSITORY = new FakeSites();
        PERSON_FAKE_REPOSITORY = new FakePersons();
        KEYWORD_FAKE_REPOSITORY = new FakeKeywords();
        RANK_FAKE_REPOSITORY = new FakeRanks();
    }

    private Repositories() {
    }

    public static HibernateRepository<Site> getSiteHibernateRepository() {
        return SITE_HIBERNATE_REPOSITORY;
    }

    public static HibernateRepository<Person> getPersonHibernateRepository() {
        return PERSON_HIBERNATE_REPOSITORY;
    }

    public static HibernateRepository<Rank> getRankHibernateRepository() {
        return RANK_HIBERNATE_REPOSITORY;
    }

    public static HibernateRepository<Keyword> getKeywordHibernateRepository() {
        return KEYWORD_HIBERNATE_REPOSITORY;
    }

    public static FakeSites getSiteFakeRepository() {
        return SITE_FAKE_REPOSITORY;
    }

    public static FakeKeywords getKeywordFakeRepository() {
        return KEYWORD_FAKE_REPOSITORY;
    }

    public static FakePersons getPersonFakeRepository() {
        return PERSON_FAKE_REPOSITORY;
    }

    public static FakeRanks getRankFakeRepository() {
        return RANK_FAKE_REPOSITORY;
    }
}
