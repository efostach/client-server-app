package com.efostach.employeesmanager.controller;

import com.efostach.employeesmanager.model.Department;
import com.efostach.employeesmanager.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DepartmentController {
    private DepartmentService departmentService;

    @Autowired(required = true)
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

    @RequestMapping("/remove/{id}")
    public String removeDepartment(@PathVariable("id") int id){
        this.departmentService.remove(id);

        return "redirect:/departments";
    }

    @RequestMapping("edit/{id}")
    public String editDepartment(@PathVariable("id") int id, Model model){
        model.addAttribute("department", this.departmentService.getById(id));
        model.addAttribute("listDepartments", this.departmentService.listAll());

        return "departments";
    }

    @RequestMapping("departmentdata/{id}")
    public String departmentData(@PathVariable("id") int id, Model model){
        model.addAttribute("department", this.departmentService.getById(id));

        return "departmentdata";
    }

}
