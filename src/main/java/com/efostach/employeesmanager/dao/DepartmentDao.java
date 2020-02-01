package com.efostach.employeesmanager.dao;

import com.efostach.employeesmanager.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDao extends JpaRepository<Department, Integer> {

}
