package com.luv2code.springboot.Employee_Thymeleaf.controller;

import com.luv2code.springboot.Employee_Thymeleaf.entity.Employee;
import com.luv2code.springboot.Employee_Thymeleaf.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        this.employeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public String listEmployee(Model theModel) {
        //Get the employees from db via Employee Service and Employee Repository
        List<Employee> theEmployee = employeeService.findAll();

        //Add into model
        theModel.addAttribute("employees", theEmployee);

        return "employees/list-employees";
    }

   @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
   }

   @PostMapping("/save")
   public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        employeeService.save(theEmployee);
        return "redirect:/employees/list";
   }

   @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId")  int theId, Model theModel){
        Employee theEmployee = employeeService.findById(theId);
        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
   }

   @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId){
        employeeService.deleteById(theId);

        return "redirect:/employees/list";
   }
}
