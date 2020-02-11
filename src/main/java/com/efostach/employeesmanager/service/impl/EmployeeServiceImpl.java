package com.efostach.employeesmanager.service.impl;

import com.efostach.employeesmanager.model.Employee;
import com.efostach.employeesmanager.model.Status;
import com.efostach.employeesmanager.repository.EmployeeRepository;
import com.efostach.employeesmanager.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static com.efostach.employeesmanager.service.impl.ServiceUtils.getTimestampUTC;

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
    private EmployeeRepository employeeRepository;

    private DepartmentServiceImpl departmentService;

    @Override
    @Transactional
    public void add(Employee employee) {
        if (employee.getFirstName() != null
                && employee.getLastName() != null
                && employee.getDateOfBirth() != null
                && employee.getSalary() != null
                && employee.getStartDate() != null) {

            employee.setCreated(getTimestampUTC());
            employee.setUpdated(getTimestampUTC());
            employee.setStatus(Status.ACTIVE);

            this.employeeRepository.save(employee);
        }
    }

    @Override
    @Transactional
    public void update(Employee employee) {

        Employee existEmployee = getById(employee.getId());

        String fistName = employee.getFirstName();
        String lastName = employee.getLastName();
        String dateOfBirth = employee.getDateOfBirth();
        BigDecimal salary = employee.getSalary();
        String startDate = employee.getStartDate();
        Long departmentId = employee.getDepartment();

        if (existEmployee != null
                && existEmployee.getStatus().equals(Status.ACTIVE)) {

            if (fistName != null) {
                existEmployee.setFirstName(fistName);
            }

            if (lastName != null) {
                existEmployee.setFirstName(lastName);
            }

            if (dateOfBirth != null) {
                existEmployee.setDateOfBirth(dateOfBirth);
            }

            if (salary != null) {
                existEmployee.setSalary(salary);
            }

            if (startDate != null) {
                existEmployee.setStartDate(startDate);
            }

            if (departmentId != null) {
                existEmployee.setDepartment(departmentId);
            }

            existEmployee.setUpdated(getTimestampUTC());
            this.employeeRepository.save(existEmployee);
        } else {
            log.info("IN update - Employee with ID: {} has status {}", existEmployee.getId(), existEmployee.getStatus());
        }
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Employee employee = getById(id);

        if (employee != null) {
            employee.setStatus(Status.DELETED);
            employee.setUpdated(getTimestampUTC());

            this.employeeRepository.save(employee);
        } else {
            log.info("IN remove - Employee doesn't exist. ID: {}", id);
        }
    }

    @Override
    @Transactional
    public Employee getById(Long id) {
        Employee employee = this.employeeRepository.getOne(id);

        if (employee.getStatus().equals(Status.ACTIVE)) {
            return employee;
        }
        log.info("IN getById - Employee with ID: {} has status {}", id, employee.getStatus());
        return null;
    }

    @Override
    @Transactional
    public List<Employee> listAll() {
        List<Employee> employees = this.employeeRepository.findAll();

        employees.removeIf(employee -> !employee.getStatus().equals(Status.ACTIVE));

        return employees;
    }
}
