package ru.unionfreearts.webservice.dao.fake;

import ru.unionfreearts.webservice.dao.IRepository;
import ru.unionfreearts.webservice.dao.specifications.Specification;
import ru.unionfreearts.webservice.model.Page;
import ru.unionfreearts.webservice.model.Site;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FakePages implements IRepository<Page> {
    @Override
    public Page get(long id) {
        Page page = new Page(
                id,
                "lenta.ru/list123",
                new Site(id, "lenta.ru"),
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()));
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
        Page page = new Page(
                1L,
                "lenta.ru/list123",
                new Site(1L, "lenta.ru"),
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()));
        ArrayList<Page> pages = new ArrayList<>();
        pages.add(page);
        pages.add(page);
        return pages;
    }
}
