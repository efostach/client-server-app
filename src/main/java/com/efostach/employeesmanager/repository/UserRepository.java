package com.efostach.employeesmanager.repository;

import com.efostach.employeesmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link User} class.
 *
 * @author Helen Fostach
 * @version 1.0
 */

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
