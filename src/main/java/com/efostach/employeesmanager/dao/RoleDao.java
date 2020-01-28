package com.efostach.employeesmanager.dao;

import com.efostach.employeesmanager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}
