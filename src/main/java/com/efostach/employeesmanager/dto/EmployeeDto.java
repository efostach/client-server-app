package com.efostach.employeesmanager.dto;

import com.efostach.employeesmanager.model.Employee;
import com.efostach.employeesmanager.model.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Employee DTO class for user requests
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dateOfBirth;
    private BigDecimal salary;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startDate;
    private Long department;

    public static List<EmployeeDto> fromEmployee(List<Employee> employees){
        List<EmployeeDto> employeeDto = new ArrayList<>();

        for (Employee employee : employees) {
            employeeDto.add(fromEmployee(employee));
        }

        return employeeDto;
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
