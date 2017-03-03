package balancika.ame.controller;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import balancika.ame.entities.AuthorizationEmployee;
import balancika.ame.entities.MeDataSource;
import balancika.ame.service.AuthorizationEmployeeService;

@RestController
@RequestMapping("/rest/authorizationemployee/")
public class AuthorizationEmployeeController {
	
	@Autowired
	private MeDataSource dataSource;
	
	@Autowired
	private AuthorizationEmployeeService authService;
	
	@RequestMapping(value = {"/get-employee/{AuthD}"}, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAuthorizationGroup(@PathVariable("AuthD") String empId) throws SQLException{
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			List<AuthorizationEmployee> auth = authService.listByEmpIdAuthorizationEmployee(dataSource, empId);
			
			if(auth == null){
				map.put("MESSAGE", "FAILED");
				map.put("STATUS", HttpStatus.NOT_FOUND.value());
				return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			}else{
				map.put("MESSAGE", "sucess");
				map.put("DATA", auth);
				map.put("STATUS", HttpStatus.OK.value());
				return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			}
			
			
		}catch (Exception e) {
			map.put("MESSAGE", "fail");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value = {"/deleteById/{empId}/{process}/{authId}"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getAuthorizationGroup(@PathVariable("empId") String empId,@PathVariable("process") String process,@PathVariable("authId") String authId) throws SQLException{
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> auth = new HashMap<String, Object>();
			auth = authService.deleteByIdAuthorizationEmployee(dataSource, empId, process, authId);
			
			if(auth == null){
				map.put("MESSAGE", "faile");
				map.put("STATUS", HttpStatus.NOT_FOUND.value());
				return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			}else{
				map.put("MESSAGE", auth.get("MESSAGE"));
				map.put("DESCRIPTION", auth.get("DESCRIPTION"));
				map.put("STATUS", HttpStatus.OK.value());
				return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			}
			
			
		}catch (Exception e) {
			map.put("MESSAGE", "fail");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		
	}
}
