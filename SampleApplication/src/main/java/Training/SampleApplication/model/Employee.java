package Training.SampleApplication.model;

import javax.persistence.*;

@Entity
@Table(name="employee")
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long empId;
	@Column(name="name")
	private String name;
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return empId + "-" + name;
	}
	
	
}
