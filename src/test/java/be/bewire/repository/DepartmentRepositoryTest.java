package be.bewire.repository;

import be.bewire.model.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository repository;

    @Test
    public void testFindAll() throws Exception {
        List<Department> result = repository.findAll();

        assertThat(result).extracting("id", "name")
            .contains(tuple(1, "Cronos"), tuple(2, "Contribute"),
                tuple(3, "Bewire"), tuple(4, "C4J"));
    }

    @Test
    public void testFindOne() throws Exception {
        Department result = repository.findOne(4);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(4);
        assertThat(result.getName()).isEqualTo("C4J");
    }

    @Test
    public void testSave() throws Exception {
        Department department = new Department();
        department.setName("Test Department");

        Department result = repository.save(department);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo("Test Department");
        assertThat(result.getCreated()).isEqualToIgnoringSeconds(new Date());
        assertThat(result.getModified()).isEqualToIgnoringSeconds(new Date());
    }
}