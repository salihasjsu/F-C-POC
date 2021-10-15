package Training.SampleApplication.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name="employee")
public class Employee implements Serializable{
	 private static final long serialVersionUID = -7817224776021728682L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long empId;
	@Column(name="name")
	private String name;
	public Employee() {}
	public Employee(Long empId, String name) {
		this.empId = empId;
		this.name = name;
	}
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
