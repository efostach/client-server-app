package com.efostach.employeesmanager.dto;

import com.efostach.employeesmanager.model.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.sql.Date;

/**
 * DTO class for user requests by all rolls
 *
 * @author Helen Fostach
 * @version 1.0
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private int salary;
    private String startDate;
    private int department;

    public Employee toEmployee(){
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDateOfBirth(dateOfBirth);
        employee.setSalary(salary);
        employee.setStartDate(startDate);
        employee.setDepartment(department);

        return employee;
    }

    public static EmployeeDto fromEmployee(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setDateOfBirth(employee.getDateOfBirth());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setStartDate(employee.getStartDate());
        employeeDto.setDepartment(employee.getDepartment());

        return employeeDto;
    }
}
