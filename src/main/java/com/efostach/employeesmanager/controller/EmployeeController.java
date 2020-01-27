package com.efostach.employeesmanager.controller;

import com.efostach.employeesmanager.model.Employee;
import com.efostach.employeesmanager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired(required = true)
    @Qualifier(value = "employeeService")
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "employees", method = RequestMethod.GET)
    public String listEmployees(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("listEmployees", this.employeeService.listAll());

        return "employees";
    }

    @RequestMapping(value = "/employees/add", method = RequestMethod.POST)
    public String addEmployee(@ModelAttribute("employee") Employee employee){
        if(employee.getId() == 0){
            this.employeeService.add(employee);
        }else {
            this.employeeService.update(employee);
        }

        return "redirect:/employees";
    }

    @RequestMapping("/remove/{id}")
    public String removeEmployee(@PathVariable("id") int id){
        this.employeeService.remove(id);

        return "redirect:/employees";
    }

    @RequestMapping("edit/{id}")
    public String editEmployee(@PathVariable("id") int id, Model model){
        model.addAttribute("employee", this.employeeService.getById(id));
        model.addAttribute("listEmployees", this.employeeService.listAll());

        return "employees";
    }

    @RequestMapping("employeedata/{id}")
    public String employeeData(@PathVariable("id") int id, Model model){
        model.addAttribute("employee", this.employeeService.getById(id));

        return "employeedata";
    }
}
