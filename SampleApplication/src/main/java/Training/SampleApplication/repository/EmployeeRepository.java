package Training.SampleApplication.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Training.SampleApplication.model.*;
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}

