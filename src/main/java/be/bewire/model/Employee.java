package be.bewire.model;

public class Employee {
    private Integer id;
    private String lastname;
    private String firstname;
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
