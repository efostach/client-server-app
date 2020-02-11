package com.efostach.employeesmanager.service;

import java.util.List;

/**
 * General Service interface contained CRUD operations.
 *
 * @author Helen Fostach
 * @version 1.0
 */

public interface GenericService<T,ID> {
    void add(T t);

    void update(T t);

    void remove(ID id);

    T getById(ID id);

    List<T> listAll();
}