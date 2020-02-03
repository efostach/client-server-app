package com.efostach.employeesmanager.dao;

import com.efostach.employeesmanager.model.Department;
import com.efostach.employeesmanager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Employee} class.
 *
 * @author Helen Fostach
 * @version 1.0
 */

public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
