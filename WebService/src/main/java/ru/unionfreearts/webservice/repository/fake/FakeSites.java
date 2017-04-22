package ru.unionfreearts.webservice.repository.fake;

import ru.unionfreearts.webservice.entity.Site;
import ru.unionfreearts.webservice.repository.IRepository;
import ru.unionfreearts.webservice.repository.specifications.Specification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FakeSites implements IRepository<Site> {
    @Override
    public Site get(long id) {
        Site site = new Site(id, "lenta.ru");
        return site;
    }

    @Override
    public boolean add(Site entity) {
        return true;
    }

    @Override
    public boolean set(Site entity) {
        return true;
    }

    @Override
    public boolean remove(Site entity) {
        return true;
    }

    @Override
    public List<Site> query(Specification specification) {
        Site site = new Site(1L, "lenta.ru");
        ArrayList<Site> sites = new ArrayList<>();
        sites.add(site);
        sites.add(site);
        return sites;
    }
}
