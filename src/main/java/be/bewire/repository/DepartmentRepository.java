package be.bewire.repository;

import be.bewire.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class DepartmentRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String INSERT = "insert into department(id, name) values(?,?)";
    private final String QUERY_ALL = "select * from department";
    private final String QUERY_BY_ID = "select * from department where id = ?";

    private final RowMapper<Department> rowMapper = (ResultSet rs, int row) -> {
        Department department = new Department();
        department.setId(rs.getInt("id"));
        department.setName(rs.getString("name"));
        return department;
    };

    @Autowired
    public DepartmentRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Department> findAll() {
        return this.jdbcTemplate.query(QUERY_ALL, rowMapper);
    }

    public Department findOne(Integer id) {
        return this.jdbcTemplate
            .queryForObject(QUERY_BY_ID, new Object[] { id }, rowMapper);
    }

    public Department save(Department department) {
        assert department.getName() != null;

        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT);
            ps.setInt(1, department.getId());
            ps.setString(2, department.getName());
            return ps;
        });
        return department;
    }
}
