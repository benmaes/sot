package be.bewire.controller;

import be.bewire.model.Employee;
import be.bewire.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testGetEmployees() throws Exception {
        List<Employee> employees = new ArrayList<>();
        Employee e1 = new Employee();
        e1.setId(1);
        e1.setLastname("Maes");
        e1.setFirstname("Ben");
        e1.setDepartmentId(4);
        employees.add(e1);
        Employee e2 = new Employee();
        e2.setId(2);
        e2.setLastname("Van Roy");
        e2.setFirstname("Davy");
        e2.setDepartmentId(4);
        employees.add(e2);
        when(employeeService.getAll()).thenReturn(employees);

        mockMvc.perform(get("/employees")).andExpect(status().isOk())
            .andExpect(jsonPath("$.[0].id", is(1)))
            .andExpect(jsonPath("$.[0].lastname", is("Maes")))
            .andExpect(jsonPath("$.[0].firstname", is("Ben")))
            .andExpect(jsonPath("$.[0].departmentId", is(4)))
            .andExpect(jsonPath("$.[1].id", is(2)))
            .andExpect(jsonPath("$.[1].lastname", is("Van Roy")))
            .andExpect(jsonPath("$.[1].firstname", is("Davy")))
            .andExpect(jsonPath("$.[1].departmentId", is(4)));
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setLastname("Maes");
        employee.setFirstname("Ben");
        employee.setDepartmentId(4);
        when(employeeService.getById(1)).thenReturn(employee);

        mockMvc.perform(get("/employees/{id}", 1)).andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.lastname", is("Maes")))
            .andExpect(jsonPath("$.firstname", is("Ben")))
            .andExpect(jsonPath("$.departmentId", is(4)));
    }
}