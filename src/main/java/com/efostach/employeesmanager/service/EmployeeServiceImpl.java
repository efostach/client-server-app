package com.efostach.employeesmanager.service;

import com.efostach.employeesmanager.dao.EmployeeDao;
import com.efostach.employeesmanager.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementations of {@link EmployeeService} interface.
 *
 * @author Helen Fostach
 * @version 1.0
 */

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    @Transactional
    public void add(Employee employee) {
        this.employeeDao.save(employee);
    }

    @Override
    @Transactional
    public void update(Employee employee) {
        this.employeeDao.save(employee);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        this.employeeDao.delete(id);
    }

    @Override
    @Transactional
    public Employee getById(Long id) {
        return this.employeeDao.getOne(id);
    }

    @Override
    @Transactional
    public List<Employee> listAll() {
        return this.employeeDao.findAll();
    }
}
