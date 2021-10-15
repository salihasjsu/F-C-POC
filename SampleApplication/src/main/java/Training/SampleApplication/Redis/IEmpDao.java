package Training.SampleApplication.Redis;

import java.util.Map;

import Training.SampleApplication.model.Employee;

public interface IEmpDao {

	    void saveEmployee(Employee emp);
	    Employee getOneEmployee(Long id);
	    void updateEmployee(Employee emp);
	    Map<Long, Employee> getAllEmployees();
	    void deleteEmployee(Long id);
	    void saveAllEmployees(Map<Long, Employee> map);

}
