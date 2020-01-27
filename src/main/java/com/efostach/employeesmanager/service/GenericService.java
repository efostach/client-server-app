package com.efostach.employeesmanager.service;

import java.util.List;

public interface GenericService<T,ID> {
    void add(T t);

    void update(T t);

    void remove(ID id);

    T getById(ID id);

    List<T> listAll();
}