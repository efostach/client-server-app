package com.efostach.employeesmanager.dto;

import com.efostach.employeesmanager.model.Department;
import com.efostach.employeesmanager.model.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Department DTO class for user requests
 *
 * @author Helen Fostach
 * @version 1.0
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentDto {
    private Long id;
    private String name;

    public static List<DepartmentDto> fromDepartment(List<Department> departments){
        List<DepartmentDto> departmentDto = new ArrayList<>();

        for (Department department : departments) {
            departmentDto.add(fromDepartment(department));
        }

        return departmentDto;
    }

    public static DepartmentDto fromDepartment(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        return departmentDto;
    }
}
