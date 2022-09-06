package com.cg.service;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<T> {
    List<T> findAll();

    Optional<T> findById(Long id);

    Optional<T> findById(String id);

    T getById(Long id);

    T getById(String id);

    T save(T t);

    void remove(Long id);

    void remove(String id);

}


