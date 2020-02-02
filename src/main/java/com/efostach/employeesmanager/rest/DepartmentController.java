package com.efostach.employeesmanager.controller;

import com.efostach.employeesmanager.model.Department;
import com.efostach.employeesmanager.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for {@link Department} connected requests.
 *
 * @author Helen Fostach
 * @version 1.0
 */

@RestController
@RequestMapping("/api/departments/data/")
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Qualifier(value = "departmentService")
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "departments", method = RequestMethod.GET)
    public String listDepartments(Model model){
        model.addAttribute("department", new Department());
        model.addAttribute("listDepartments", this.departmentService.listAll());

        return "departments";
    }

    @RequestMapping(value = "/departments/add", method = RequestMethod.POST)
    public String addDepartment(@ModelAttribute("department") Department department){
        if(department.getId() == 0){
            this.departmentService.add(department);
        }else {
            this.departmentService.update(department);
        }

        return "redirect:/departments";
    }

    @RequestMapping("/departments/remove/{id}")
    public String removeDepartment(@PathVariable("id") int id){
        this.departmentService.remove(id);

        return "redirect:/departments";
    }

    @RequestMapping("/departments/edit/{id}")
    public String editDepartment(@PathVariable("id") int id, Model model){
        model.addAttribute("department", this.departmentService.getById(id));
        model.addAttribute("listDepartments", this.departmentService.listAll());

        return "departments";
    }

    @RequestMapping("/departments/data/{id}")
    public String departmentData(@PathVariable("id") int id, Model model){
        model.addAttribute("department", this.departmentService.getById(id));

        return "departmentdata";
    }

//    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> departmentData(@PathVariable("id") int id, Model model) {
//        if (id == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        Department department = this.departmentService.getById(id);
//
//        if (department == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(department, HttpStatus.OK);
//    }

}
