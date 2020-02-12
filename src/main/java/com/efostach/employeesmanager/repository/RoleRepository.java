package com.efostach.employeesmanager.repository;

import com.efostach.employeesmanager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Role} class.
 *
 * @author Helen Fostach
 * @version 1.0
 */

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}