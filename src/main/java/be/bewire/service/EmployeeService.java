package be.bewire.service;

import be.bewire.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    Employee getById(Integer id);

    List<Employee> getByDepartmentId(Integer id);
}
