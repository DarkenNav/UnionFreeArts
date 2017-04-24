package ru.unionfreearts.webservice.repository.fake;

import ru.unionfreearts.webservice.entity.Page;
import ru.unionfreearts.webservice.entity.Person;
import ru.unionfreearts.webservice.entity.Rank;
import ru.unionfreearts.webservice.entity.Site;
import ru.unionfreearts.webservice.repository.IRepository;
import ru.unionfreearts.webservice.repository.specifications.Specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FakeRanks implements IRepository<Rank> {

    @Override
    public Rank get(long id) {
        return null;
    }

    @Override
    public boolean add(Rank entity) {
        return true;
    }

    @Override
    public boolean set(Rank entity) {
        return true;
    }

    @Override
    public boolean remove(Rank entity) {
        return true;
    }

    @Override
    public List<Rank> query(Specification specification) {
        Rank rank = new Rank(new Person(1L, "Путин"),
                new Page(1L, "lenta.ru", new Site(1L, "lenta.ru"), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())),
                5);
        List<Rank> ranks = new ArrayList<>();
        ranks.add(rank);
        ranks.add(rank);
        ranks.add(rank);
        return ranks;
    }
}
