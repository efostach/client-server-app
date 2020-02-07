package com.efostach.employeesmanager.repository;

import com.efostach.employeesmanager.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Department} class.
 *
 * @author Helen Fostach
 * @version 1.0
 */

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
