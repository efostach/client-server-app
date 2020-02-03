package com.efostach.employeesmanager.service;

import com.efostach.employeesmanager.dao.DepartmentDao;
import com.efostach.employeesmanager.model.Department;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementations of {@link DepartmentService} interface.
 *
 * @author Helen Fostach
 * @version 1.0
 */

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    @Transactional
    public void add(Department department) {

        this.departmentDao.save(department);
    }

    @Override
    @Transactional
    public void update(Department department) {
        this.departmentDao.save(department);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        this.departmentDao.delete(id);
    }

    @Override
    @Transactional
    public Department getById(Long id) {
        return this.departmentDao.getOne(id);
    }

    @Override
    @Transactional
    public List<Department> listAll() {
        return this.departmentDao.findAll();
    }
}
