package com.rozhdev.ordee.commons.service;

import java.util.List;

public interface EntityService<T> {
    T save(T entity);
    List<T> findAll();
}
