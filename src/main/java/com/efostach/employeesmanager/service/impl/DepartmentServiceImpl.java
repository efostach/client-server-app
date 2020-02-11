package com.efostach.employeesmanager.service.impl;

import com.efostach.employeesmanager.model.Department;
import com.efostach.employeesmanager.model.Status;
import com.efostach.employeesmanager.repository.DepartmentRepository;
import com.efostach.employeesmanager.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.efostach.employeesmanager.service.impl.ServiceUtils.getTimestampUTC;

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

    @Override
    @Transactional
    public void add(Department department) {
        if (department.getName() != null) {

            department.setCreated(getTimestampUTC());
            department.setUpdated(getTimestampUTC());
            department.setStatus(Status.ACTIVE);


            this.departmentRepository.save(department);
        }
    }

    @Override
    @Transactional
    public void update(Department department) {
        Department existDepartment = getById(department.getId());

        if (existDepartment != null
                && existDepartment.getStatus().equals(Status.ACTIVE)) {

            if (department.getName() != null) {
                existDepartment.setName(department.getName());
            }

            existDepartment.setUpdated(getTimestampUTC());
            this.departmentRepository.save(existDepartment);
        } else {
            log.info("IN update - Department with ID: {} has status {}", existDepartment.getId(), existDepartment.getStatus());
        }
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Department department = getById(id);

        if (department != null) {
            department.setStatus(Status.DELETED);
            department.setUpdated(getTimestampUTC());

            this.departmentRepository.save(department);
        } else {
            log.info("IN remove - Department doesn't exist. ID: {}", id);
        }
    }

    @Override
    @Transactional
    public Department getById(Long id) {
        Department department = this.departmentRepository.getOne(id);

        if (department.getStatus().equals(Status.ACTIVE)) {
            return department;
        }

        log.info("IN getById - Department with ID: {} has status {}", id, department.getStatus());

        return null;
    }

    @Override
    @Transactional
    public List<Department> listAll() {
        List<Department> departments = this.departmentRepository.findAll();

        departments.removeIf(department -> !department.getStatus().equals(Status.ACTIVE));

        return departments;
    }
}
