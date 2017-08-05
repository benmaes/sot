package be.bewire.controller;

import be.bewire.model.Department;
import be.bewire.model.Employee;
import be.bewire.service.DepartmentService;
import be.bewire.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private DepartmentService departmentService;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testGetDepartments() throws Exception {
        List<Department> departments = new ArrayList<>();
        Department d1 = new Department();
        d1.setId(1);
        d1.setName("Cronos");
        departments.add(d1);
        Department d2 = new Department();
        d2.setId(2);
        d2.setName("Bewire");
        departments.add(d2);
        when(departmentService.getAll()).thenReturn(departments);

        mockMvc.perform(get("/departments")).andExpect(status().isOk())
            .andExpect(jsonPath("$.[0].id", is(1)))
            .andExpect(jsonPath("$.[0].name", is("Cronos")))
            .andExpect(jsonPath("$.[1].id", is(2)))
            .andExpect(jsonPath("$.[1].name", is("Bewire")));
    }

    @Test
    public void testGetDepartmentById() throws Exception {
        Department department = new Department();
        department.setId(1);
        department.setName("Cronos");
        when(departmentService.getById(1)).thenReturn(department);

        mockMvc.perform(get("/departments/{id}", 1)).andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.name", is("Cronos")));
    }

    @Test
    public void testGetEmployeesByDepartmentId() throws Exception {
        Employee employee = new Employee();
        employee.setId(22);
        employee.setLastname("Maes");
        employee.setFirstname("Ben");
        employee.setDepartmentId(4);
        when(employeeService.getByDepartmentId(4))
            .thenReturn(Collections.singletonList(employee));

        mockMvc.perform(get("/departments/{id}/employees", 4))
            .andExpect(status().isOk()).andExpect(jsonPath("$.[0].id", is(22)))
            .andExpect(jsonPath("$.[0].lastname", is("Maes")))
            .andExpect(jsonPath("$.[0].firstname", is("Ben")))
            .andExpect(jsonPath("$.[0].departmentId", is(4)));
    }

    @Test
    public void testAddDepartment() throws Exception {
        Department department = new Department();
        department.setId(123);
        department.setName("new department");
        when(departmentService.save(any(Department.class)))
            .thenReturn(department);

        mockMvc.perform(
            post("/departments").content(mapper.writeValueAsString(department))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", is(123)))
            .andExpect(jsonPath("$.name", is("new department")))
            .andExpect(header().string("Location", "http://localhost/departments/123"));
    }
}