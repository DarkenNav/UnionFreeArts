package ru.unionfreearts.webapp.specifications;

import java.util.List;

public interface Specification<T> {
    List<T> toList();
}
