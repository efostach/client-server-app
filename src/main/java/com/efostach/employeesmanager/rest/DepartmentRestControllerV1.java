package com.efostach.employeesmanager.rest;

import com.efostach.employeesmanager.dto.DepartmentDto;
import com.efostach.employeesmanager.model.Department;
import com.efostach.employeesmanager.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller for {@link Department} connected requests.
 *
 * @author Helen Fostach
 * @version 1.0
 */

@Slf4j
@RestController
@RequestMapping("/api/v1/departments/")
public class DepartmentRestControllerV1 {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Department department = this.departmentService.getById(id);

        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(DepartmentDto.fromDepartment(department), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody @Valid Department department) {
        log.info("IN saveDepartment - Department: {}", department.toString());
        HttpHeaders headers = new HttpHeaders();
        log.info("IN saveDepartment - HEADERS: {}", headers.toString());

        this.departmentService.add(department);

        return new ResponseEntity<>(DepartmentDto.fromDepartment(department), headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody @Valid Department department) {
        HttpHeaders headers = new HttpHeaders();

        if (department == null && department.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.departmentService.add(department);

        return new ResponseEntity<>(DepartmentDto.fromDepartment(department), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> deleteDepartment(@PathVariable("id") Long id) {
        Department department = this.departmentService.getById(id);

        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.departmentService.remove(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<Department> departments = this.departmentService.listAll();

        if (departments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(DepartmentDto.fromDepartment(departments), HttpStatus.OK);
    }
}
