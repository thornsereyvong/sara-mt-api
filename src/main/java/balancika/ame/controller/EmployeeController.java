package balancika.ame.controller;



import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import balancika.ame.entities.Employee;
import balancika.ame.entities.MeDataSource;
import balancika.ame.service.EmployeeService;


@RestController
@RequestMapping("/rest/employee/")
public class EmployeeController {

	@Autowired
	private MeDataSource  dataSource;
	
	@Autowired
	private EmployeeService empService;

	@RequestMapping(value = {"/list"}, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listEmployee(HttpServletRequest req) throws SQLException{
		Map<String, Object> map = new HashMap<String, Object>();
		dataSource = dataSource.getMeDataSourceByHttpServlet(req);
		List<Employee>	 emps = empService.listEmployee(dataSource)	;
		if(emps != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", emps);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
}
