package be.bewire.service;

import be.bewire.model.Department;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    private List<Department> departments;

    @PostConstruct
    public void init() {
        departments = new ArrayList<Department>() {{
            add(new Department(1, "Cronos"));
            add(new Department(2, "Contribute"));
            add(new Department(3, "Bewire"));
            add(new Department(4, "C4J"));
        }};
    }

    @Override
    public List<Department> getAll() {
        return departments;
    }

    @Override
    public Department getById(final Integer id) {
        Optional<Department> department = departments.stream()
            .filter(d -> d.getId().equals(id)).findFirst();
        return department.get();
    }

    @Override
    public Department save(final Department department) {
        departments.add(department);
        return department;
    }
}