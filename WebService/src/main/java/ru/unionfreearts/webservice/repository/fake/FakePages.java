package ru.unionfreearts.webservice.repository.fake;

import ru.unionfreearts.webservice.entity.Page;
import ru.unionfreearts.webservice.entity.Site;
import ru.unionfreearts.webservice.repository.Repository;
import ru.unionfreearts.webservice.specifications.Specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class FakePages implements Repository<Page> {
    @Override
    public Page get(long id) {
        Page page = new Page();
        page.setId(id);
        page.setSite(new Site("lenta.ru"));
        page.setUrl("lenta.ru/blablabla");
        page.setFoundDateTime(new Date(System.currentTimeMillis()));
        page.setLastDateTime(new Date(System.currentTimeMillis()));
        page.setRanks(new HashSet<>());
        return page;
    }

    @Override
    public boolean add(Page entity) {
        return true;
    }

    @Override
    public boolean set(Page entity) {
        return true;
    }

    @Override
    public boolean remove(Page entity) {
        return true;
    }

    @Override
    public List<Page> query(Specification specification) {
        Page page = new Page();
        page.setId(1L);
        page.setSite(new Site("lenta.ru"));
        page.setUrl("lenta.ru/blablabla");
        page.setFoundDateTime(new Date(System.currentTimeMillis()));
        page.setLastDateTime(new Date(System.currentTimeMillis()));
        page.setRanks(new HashSet<>());
        ArrayList<Page> pages = new ArrayList<>();
        pages.add(page);
        pages.add(page);
        return pages;
    }
}
