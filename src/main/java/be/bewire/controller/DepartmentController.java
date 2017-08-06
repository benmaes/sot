package be.bewire.controller;

import be.bewire.model.Department;
import be.bewire.model.Employee;
import be.bewire.service.DepartmentService;
import be.bewire.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CounterService counterService;

    @GetMapping(path = "/departments")
    public List<Department> getDepartments() {
        counterService.increment("counter.services.departments.invoke");
        return departmentService.getAll();
    }

    @GetMapping(path = "/departments/{id}")
    public Department getDepartmentById(
        @PathVariable("id")
            Integer id) {
        return departmentService.getById(id);
    }

    @GetMapping(path = "/departments/{id}/employees")
    public List<Employee> getEmployeesByDepartmentId(
        @PathVariable("id")
            Integer id) {
        return employeeService.getByDepartmentId(id);
    }

    @PostMapping(path = "/departments")
    public ResponseEntity<?> addDepartment(
        @RequestBody
            Department department) {
        Department saved = departmentService.save(department);
        assert saved != null;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/" + saved.getId()).buildAndExpand().toUri());

        return new ResponseEntity<>(saved, httpHeaders, HttpStatus.CREATED);
    }
}
