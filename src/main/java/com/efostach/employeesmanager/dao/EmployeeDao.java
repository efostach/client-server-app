package com.efostach.employeesmanager.dao;

import com.efostach.employeesmanager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

}
