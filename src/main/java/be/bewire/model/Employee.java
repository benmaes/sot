package be.bewire.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@SequenceGenerator(name = "entity_sequence",
    sequenceName = "employee_seq", allocationSize = 1)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
        generator = "entity_sequence")
    private Integer id;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "department_id")
    private Integer departmentId;

    public Employee() {
    }

    public Employee(final Integer id, final String lastname,
        final String firstname, final Integer departmentId) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.departmentId = departmentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(final Integer departmentId) {
        this.departmentId = departmentId;
    }
}
