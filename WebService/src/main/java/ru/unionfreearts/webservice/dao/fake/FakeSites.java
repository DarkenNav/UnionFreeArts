package ru.unionfreearts.webservice.dao.fake;

import ru.unionfreearts.webservice.model.Site;
import ru.unionfreearts.webservice.dao.IRepository;
import ru.unionfreearts.webservice.dao.specifications.Specification;

import java.util.ArrayList;
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
