package ru.unionfreearts.webservice.dao;

import org.springframework.stereotype.Repository;
import ru.unionfreearts.webservice.model.Rank;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Repository(value = "rankRepository")
public class RankRepository extends AbstractRepository<Rank> {
    @Override
    public Rank get(long id) {
        throw new NotImplementedException();
    }
}
