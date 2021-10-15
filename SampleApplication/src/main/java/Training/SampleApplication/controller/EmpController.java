package Training.SampleApplication.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Training.SampleApplication.Redis.*;
import Training.SampleApplication.EmployeeNotFoundException;
import Training.SampleApplication.model.Employee;
import Training.SampleApplication.repository.EmployeeRepository;
import Training.SampleApplication.service.EmpService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class EmpController {
	@Autowired
	//private  final Training.SampleApplication.Redis.EmpDaoImpl empDao;
	private final EmployeeRepository empRepostiory;
	private final IEmpDao empDao;
	
	EmpController(EmployeeRepository repository, IEmpDao empDao){
		this.empRepostiory = repository;
		this.empDao = empDao;
	}
	@RequestMapping("/")
	public String home() {
		return "Spring boot is working";
	}
	
	@RequestMapping(value = "/emp", method = RequestMethod.GET)
	public List<Employee> getAllEmployees(){
		return empRepostiory.findAll();
	}
	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public Employee saveEmployee(@RequestBody Employee newEmployee) {
	
		Employee savedEmployee =  empRepostiory.save(newEmployee);
		empDao.saveEmployee(savedEmployee); //saving to Redis
		return savedEmployee;
	}
	 @GetMapping("/employees/{id}")
	  Employee one(@PathVariable Long id) {
		 // first check if employee is available in redis
	    if(empDao.getOneEmployee(id)!=null)
	    	return empDao.getOneEmployee(id);
	    return empRepostiory.findById(id)
	      .orElseThrow(() -> new EmployeeNotFoundException(id));
	  }
	  @PutMapping("/employees/{id}")
	  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		  //update in cache
		  empDao.updateEmployee(newEmployee);
	    return empRepostiory.findById(id)
	      .map(employee -> {
	        employee.setName(newEmployee.getName());
	        return empRepostiory.save(employee);
	      })
	      .orElseGet(() -> {
	        newEmployee.setEmpId(id);
	        return empRepostiory.save(newEmployee);
	      });
	  }

	  @DeleteMapping("/employees/{id}")
	  void deleteEmployee(@PathVariable Long id) {
		  empRepostiory.deleteById(id);
	  }
}
