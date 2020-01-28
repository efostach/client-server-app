package com.efostach.employeesmanager.dao;

import com.efostach.employeesmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
