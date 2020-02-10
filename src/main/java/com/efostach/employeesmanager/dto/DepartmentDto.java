package com.efostach.employeesmanager.dto;

import com.efostach.employeesmanager.model.Department;
import com.efostach.employeesmanager.model.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * DTO class for user requests by all rolls
 *
 * @author Helen Fostach
 * @version 1.0
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentDto {
    private Long id;
    private String name;

    public Department toDepartment(){
        Department department = new Department();
        department.setId(id);
        department.setName(name);

        return department;
    }

    public static DepartmentDto fromDepartment(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        return departmentDto;
    }
}
