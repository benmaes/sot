DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;

CREATE TABLE department(
    id      INTEGER NOT NULL,
    NAME    VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE employee(
    id              INTEGER NOT NULL,
    lastname        VARCHAR(100) NOT NULL,
    firstname       VARCHAR(100) NOT NULL,
    department_id   INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (department_id) REFERENCES department(id)
);