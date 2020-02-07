package com.efostach.employeesmanager.service;

import com.efostach.employeesmanager.model.Department;
import com.efostach.employeesmanager.repository.DepartmentRepository;
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
    private DepartmentRepository departmentRepository;

//    public void setDepartmentDao(DepartmentRepository departmentRepository) {
//        this.departmentRepository = departmentRepository;
//    }

    @Override
    @Transactional
    public void add(Department department) {

        this.departmentRepository.save(department);
    }

    @Override
    @Transactional
    public void update(Department department) {
        this.departmentRepository.save(department);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        this.departmentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Department getById(Long id) {
        return this.departmentRepository.getOne(id);
    }

    @Override
    @Transactional
    public List<Department> listAll() {
        return this.departmentRepository.findAll();
    }
}
