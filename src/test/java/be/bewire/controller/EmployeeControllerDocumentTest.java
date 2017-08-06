package be.bewire.controller;

import be.bewire.model.Employee;
import be.bewire.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class EmployeeControllerDocumentTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setLastname("Maes");
        employee.setFirstname("Ben");
        employee.setDepartmentId(4);
        when(employeeService.getById(1)).thenReturn(employee);

        mockMvc.perform(get("/employees/{id}", 1))
               .andDo(document("employee-by-id",
                               responseFields(
                                       fieldWithPath("id").description("Id of the user"),
                                       fieldWithPath("lastname").description("Lastname of the user"),
                                       fieldWithPath("firstname").description("Firstname of the user"),
                                       fieldWithPath("departmentId").description("Id of the department user belongs to")
                                             )
                              )
                     );
        ;
    }
}