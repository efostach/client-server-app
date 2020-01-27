package com.efostach.employeesmanager.service;

import com.efostach.employeesmanager.dao.DepartmentDao;
import com.efostach.employeesmanager.model.Department;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao;

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    @Transactional
    public void add(Department department) {
        this.departmentDao.add(department);
    }

    @Override
    @Transactional
    public void update(Department department) {
        this.departmentDao.update(department);
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        this.departmentDao.remove(id);
    }

    @Override
    @Transactional
    public Department getById(Integer id) {
        return this.departmentDao.getById(id);
    }

    @Override
    @Transactional
    public List<Department> listAll() {
        return this.departmentDao.listAll();
    }
}
