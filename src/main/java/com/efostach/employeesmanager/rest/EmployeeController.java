package com.efostach.employeesmanager.controller;

import com.efostach.employeesmanager.model.Employee;
import com.efostach.employeesmanager.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for {@link Employee} connected requests.
 *
 * @author Helen Fostach
 * @version 1.0
 */

@Controller
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Qualifier(value = "employeeService")
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "employees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String listEmployees(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("listEmployees", this.employeeService.listAll());

        return "employees";
    }

    @RequestMapping(value = "/employees/add", method = RequestMethod.POST)
    public String addEmployee(@ModelAttribute("employee") Employee employee){
        if(employee != null) {
            if(employee.getId() != null){
                this.employeeService.add(employee);
                log.info("Employee successfully added: " + employee);
            }else {
                this.employeeService.update(employee);
                log.info("Employee successfully updated: " + employee);
            }
        }

        return "redirect:/employees";
    }

    @RequestMapping("/employees/remove/{id}")
    public String removeEmployee(@PathVariable("id") Long id){
        this.employeeService.remove(id);
        log.info("Employee successfully removed");
        return "redirect:/employees";
    }

    @RequestMapping("/employees/edit/{id}")
    public String editEmployee(@PathVariable("id") Long id, Model model){
        model.addAttribute("employee", this.employeeService.getById(id));
        model.addAttribute("listEmployees", this.employeeService.listAll());

        return "employees";
    }

    @RequestMapping("/employees/data/{id}")
    public String employeeData(@PathVariable("id") Long id, Model model){
        model.addAttribute("employee", this.employeeService.getById(id));

        return "employeedata";
    }
}
