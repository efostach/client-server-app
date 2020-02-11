package com.efostach.employeesmanager.rest;

import com.efostach.employeesmanager.dto.EmployeeDto;
import com.efostach.employeesmanager.model.Employee;
import com.efostach.employeesmanager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller for {@link Employee} connected requests.
 *
 * @author Helen Fostach
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1/employees/")
public class EmployeeRestControllerV1 {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Employee employee = this.employeeService.getById(id);

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>(EmployeeDto.fromEmployee(employee), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody @Valid Employee employee) {

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.employeeService.add(employee);

        return new ResponseEntity<>(EmployeeDto.fromEmployee(employee), HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody @Valid Employee employee) {

        if (employee == null || employee.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.employeeService.update(employee);

        return new ResponseEntity<>(EmployeeDto.fromEmployee(employee), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable("id") Long id) {
        Employee employee = this.employeeService.getById(id);

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.employeeService.remove(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<Employee> employees = this.employeeService.listAll();

        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(EmployeeDto.fromEmployee(employees), HttpStatus.OK);
    }
}
