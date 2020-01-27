package com.efostach.employeesmanager.dao;

import java.util.List;

public interface GenericDao<T,ID> {
    void add(T t);

    void update(T t);

    void remove(ID id);

    T getById(ID id);

    List<T> listAll();
}