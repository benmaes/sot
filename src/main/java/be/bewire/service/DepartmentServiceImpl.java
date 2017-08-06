package be.bewire.service;

import be.bewire.model.Department;
import be.bewire.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(
        final DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getById(final Integer id) {
        return departmentRepository.findOne(id);
    }

    @Override
    public Department save(final Department department) {
        return departmentRepository.save(department);
    }
}