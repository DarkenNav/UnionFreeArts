package ru.unionfreearts.webservice.repository.specifications;

import java.util.List;

public interface Specification<T> {
    List<T> toList();
}
