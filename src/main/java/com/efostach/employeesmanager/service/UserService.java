package com.efostach.employeesmanager.service;

import com.efostach.employeesmanager.model.User;

import java.util.List;

/**
 * Service interface for {@link User} class.
 *
 * @author Helen Fostach
 * @version 1.0
 */

public interface UserService {

    User register(User user);

    User confirm(Long id, String code);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void remove(Long id);
}
