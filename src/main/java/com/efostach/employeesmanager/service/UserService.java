package com.efostach.employeesmanager.service;

import com.efostach.employeesmanager.model.Department;
import com.efostach.employeesmanager.model.User;

/**
 * Service interface for {@link User} class.
 *
 * @author Helen Fostach
 * @version 1.0
 */

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
