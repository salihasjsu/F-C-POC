DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  empId LONG NOT NULL AUTO_INCREMENT,
  empName VARCHAR(100) NOT NULL
);
PRIMARY KEY(empId);

INSERT INTO employee(empName)values("emp1");
INSERT INTO employee(empName)values("emp2");
INSERT INTO employee(empName)values("emp3");