package balancika.ame.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import balancika.ame.entities.Employee;
import balancika.ame.entities.MeDataSource;

@Repository
public interface EmployeeService {
	List<Employee> listEmployee(MeDataSource dataSource) throws SQLException;
}
