package be.bewire.service;

import be.bewire.model.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private List<Employee> employees;

    @PostConstruct
    public void init() {
        employees = new ArrayList<Employee>() {{
            add(new Employee(1, "Van Roy", "Davy", 4));
            add(new Employee(2, "Maes", "Ben", 4));
            add(new Employee(3, "Benats", "Jeroen", 3));
            add(new Employee(4, "Stroobants", "Tom", 3));
            add(new Employee(5, "Embrechts", "David", 2));
            add(new Employee(6, "De Kepper", "Gerrit", 2));
            add(new Employee(7, "de Wit", "Jef", 1));
            add(new Employee(8, "Deroost", "Dirk", 1));
        }};
    }

    @Override
    public List<Employee> getAll() {
        return employees;
    }

    @Override
    public Employee getById(final Integer id) {
        Optional<Employee> employee = employees.stream()
            .filter(e -> e.getId().equals(id)).findFirst();
        return employee.get();
    }

    @Override
    public List<Employee> getByDepartmentId(final Integer id) {
        return employees.stream().filter(e -> e.getDepartmentId().equals(id))
            .collect(Collectors.toList());
    }
}
