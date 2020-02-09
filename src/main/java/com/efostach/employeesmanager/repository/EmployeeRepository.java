package com.efostach.employeesmanager.repository;

import com.efostach.employeesmanager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Employee} class.
 *
 * @author Helen Fostach
 * @version 1.0
 */

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
