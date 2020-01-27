package com.efostach.employeesmanager.service;

import com.efostach.employeesmanager.dao.EmployeeDao;
import com.efostach.employeesmanager.model.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    @Transactional
    public void add(Employee employee) {
        this.employeeDao.add(employee);
    }

    @Override
    @Transactional
    public void update(Employee employee) {
        this.employeeDao.update(employee);
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        this.employeeDao.remove(id);
    }

    @Override
    @Transactional
    public Employee getById(Integer id) {
        return this.employeeDao.getById(id);
    }

    @Override
    @Transactional
    public List<Employee> listAll() {
        return this.employeeDao.listAll();
    }
}
