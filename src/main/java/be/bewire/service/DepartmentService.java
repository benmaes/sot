package be.bewire.service;

import be.bewire.model.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getDepartments();

    Department getDepartmentById(Integer id);

    Department save(Department department);
}
