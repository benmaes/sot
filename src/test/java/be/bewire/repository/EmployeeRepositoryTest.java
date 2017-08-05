package be.bewire.repository;

import be.bewire.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    @Test
    public void testFindAll() throws Exception {
        List<Employee> result = repository.findAll();

        assertThat(result).hasSize(8);
    }

    @Test
    public void testFindOne() throws Exception {
        Employee result = repository.findOne(2);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(2);
        assertThat(result.getLastname()).isEqualTo("Maes");
        assertThat(result.getFirstname()).isEqualTo("Ben");
        assertThat(result.getDepartmentId()).isEqualTo(4);
    }

    @Test
    public void testFindByDepartmentId() throws Exception {
        List<Employee> result = repository.findByDepartmentId(4);

        assertThat(result)
            .extracting("id", "lastname", "firstname", "departmentId")
            .contains(tuple(1, "Van Roy", "Davy", 4),
                tuple(2, "Maes", "Ben", 4));
    }
}