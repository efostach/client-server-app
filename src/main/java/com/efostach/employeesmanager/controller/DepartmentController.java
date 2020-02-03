package com.efostach.employeesmanager.controller;

import com.efostach.employeesmanager.model.Department;
import com.efostach.employeesmanager.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for {@link Department} connected requests.
 *
 * @author Helen Fostach
 * @version 1.0
 */

@Slf4j
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
        if(department != null) {
            if (department.getId() == null) {
                this.departmentService.add(department);
                log.info("Department successfully added: " + department);
            } else {
                this.departmentService.update(department);
                log.info("Department successfully updated: " + department);
            }
        }

        return "redirect:/departments";
    }

    @RequestMapping("/departments/remove/{id}")
    public String removeDepartment(@PathVariable("id") Long id){
        this.departmentService.remove(id);
        log.info("Department successfully removed");
        return "redirect:/departments";
    }

    @RequestMapping("/departments/edit/{id}")
    public String editDepartment(@PathVariable("id") Long id, Model model){
        model.addAttribute("department", this.departmentService.getById(id));
        model.addAttribute("listDepartments", this.departmentService.listAll());
        return "departments";
    }

    @RequestMapping("/departments/data/{id}")
    public String departmentData(@PathVariable("id") Long id, Model model){
        model.addAttribute("department", this.departmentService.getById(id));

        return "departmentdata";
    }
}
