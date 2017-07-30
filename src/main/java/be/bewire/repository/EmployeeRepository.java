package be.bewire.repository;

import be.bewire.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String QUERY_ALL = "select * from employee";
    private final String QUERY_BY_ID = "select * from employee where id = ?";
    private final String QUERY_BY_DEPARTMENT_ID = "select * from employee where department_id = ?";

    private final RowMapper<Employee> rowMapper = (ResultSet rs, int row) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setLastname(rs.getString("lastname"));
        employee.setFirstname(rs.getString("firstname"));
        employee.setDepartmentId(rs.getInt("department_id"));
        return employee;
    };

    @Autowired
    public EmployeeRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> findAll() {
        return this.jdbcTemplate.query(QUERY_ALL, rowMapper);
    }

    public Employee findOne(Integer id) {
        return this.jdbcTemplate
            .queryForObject(QUERY_BY_ID, new Object[] { id }, rowMapper);
    }

    public List<Employee> findByDepartmentId(Integer id) {
        return this.jdbcTemplate
            .query(QUERY_BY_DEPARTMENT_ID, new Object[] { id }, rowMapper);
    }
}
