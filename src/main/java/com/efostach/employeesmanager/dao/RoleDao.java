package com.efostach.employeesmanager.dao;

import com.efostach.employeesmanager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Role} class.
 *
 * @author Helen Fostach
 * @version 1.0
 */

public interface RoleDao extends JpaRepository<Role, Long> {
}
