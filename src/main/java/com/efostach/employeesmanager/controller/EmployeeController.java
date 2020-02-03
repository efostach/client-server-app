package com.efostach.employeesmanager.rest;

import com.efostach.employeesmanager.model.Department;
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
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.awt.*;

/**
 * REST controller for {@link Employee} connected requests.
 *
 * @author Helen Fostach
 * @version 1.0
 */

@RestController
@RequestMapping("")
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
    public ResponseEntity<ModelAndView> listEmployees(Model model){
        ModelAndView mav = new ModelAndView();
        mav.addObject("employee", new Employee());
        mav.addObject("listEmployees", this.employeeService.listAll());

        return new ResponseEntity<>(mav, HttpStatus.OK);
    }

//    @RequestMapping(value = "/employees/add", method = RequestMethod.POST)
//    public String addEmployee(@ModelAttribute("employee") Employee employee){
//
//        log.info("Added employee: {}", employee);
//        if(employee.getId() == 0){
//            this.employeeService.add(employee);
//        }else {
//            this.employeeService.update(employee);
//        }
//
//        return "redirect:/employees";
//    }

    @RequestMapping(value = "/employees/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addEmployee(@RequestBody @ModelAttribute("employee") @Valid Employee employee){
        HttpHeaders headers = new HttpHeaders();
        log.info("Added employee: {}", employee);
        if (employee != null) {
            if (employee.getId() == 0) {
                this.employeeService.add(employee);
            } else {
                this.employeeService.update(employee);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("redirect:/employees",headers, HttpStatus.OK);
    }



    @RequestMapping("/employees/remove/{id}")
    public String removeEmployee(@PathVariable("id") Long id){
        this.employeeService.remove(id);

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
