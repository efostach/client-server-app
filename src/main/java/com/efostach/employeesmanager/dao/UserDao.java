package com.efostach.employeesmanager.dao;

import com.efostach.employeesmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link User} class.
 *
 * @author Helen Fostach
 * @version 1.0
 */

public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
