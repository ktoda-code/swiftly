package com.ktoda.swiftly.backend.common.service;

import java.util.List;

public interface Service<T, ID> {
    T find(T entity);

    T findById(ID id);

    List<T> findAll();

    boolean exists(T entity);

    T save(T entity);

    void remove(T entity);
}
