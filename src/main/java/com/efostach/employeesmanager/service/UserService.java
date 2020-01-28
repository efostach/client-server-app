package com.efostach.employeesmanager.service;

import com.efostach.employeesmanager.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
