package be.bewire.controller;

import be.bewire.model.Employee;
import be.bewire.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/employees")
    public List<Employee> getEmployees() {
        return employeeService.getAll();
    }

    @GetMapping(path = "/employees/{id}")
    public Employee getEmployeeById(
        @PathVariable("id")
            Integer id) {
        return employeeService.getById(id);
    }
}