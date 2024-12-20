package org.example.sistemaemprestimo.repository.dao;

import java.util.List;

public interface GenericDAO<T> {
    void insert(T entity);
    void update(T entity);
    boolean delete(T entity);

    List<T> findAll();
    T findByPrimaryKey(Object key);
}

