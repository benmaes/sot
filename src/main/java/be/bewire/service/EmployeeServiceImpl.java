package be.bewire.service;

import be.bewire.model.Employee;
import be.bewire.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(final Integer id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public List<Employee> getByDepartmentId(final Integer id) {
        return employeeRepository.findByDepartmentId(id);
    }
}
