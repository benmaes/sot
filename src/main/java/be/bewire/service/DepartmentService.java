package be.bewire.service;

import be.bewire.model.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAll();

    Department getById(Integer id);

    Department save(Department department);
}
