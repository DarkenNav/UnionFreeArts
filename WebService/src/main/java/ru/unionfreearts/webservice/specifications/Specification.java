package ru.unionfreearts.webservice.specifications;

import java.util.List;

public interface Specification<T> {
    List<T> toList();
}
