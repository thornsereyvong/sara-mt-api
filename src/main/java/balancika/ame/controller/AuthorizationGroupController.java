package balancika.ame.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import balancika.ame.entities.AuthorizationGroup;
import balancika.ame.entities.MeDataSource;
import balancika.ame.service.AuthorizationGroupService;


@RestController
@RequestMapping("/rest/authorizationgroup/")
public class AuthorizationGroupController {

	@Autowired
	private MeDataSource  dataSource;
	
	@Autowired
	private AuthorizationGroupService authService;

	@RequestMapping(value = {"/list"}, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listAuthorizationGroup(HttpServletRequest req) throws SQLException{
		Map<String, Object> map = new HashMap<String, Object>();
		dataSource = dataSource.getMeDataSourceByHttpServlet(req);
		
		List<AuthorizationGroup> auth = authService.listAuthorizationGroup(dataSource);
		
		if(auth != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", auth);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/create"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> createAuthorizationGroup(@RequestBody AuthorizationGroup authGroup ) throws SQLException{
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			Map<String, Object> m = authService.createAuthorizationGroup(authGroup, dataSource);
			System.out.println(m);
			if(m.get("MESSAGE") == null || m.get("MESSAGE").equals("")){
				map.put("MESSAGE", "FAILED");
				map.put("STATUS", HttpStatus.NOT_FOUND.value());
				return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			}else if(m.get("MESSAGE").equals("exist")){
				map.put("MESSAGE", "EXIST");
				map.put("STATUS", HttpStatus.OK.value());
				return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			}else{
				map.put("MESSAGE", "SUCCESS");
				map.put("STATUS", HttpStatus.OK.value());
				return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			}
			
		
			
		}catch (Exception e) {
			map.put("message", "fail");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = {"/delete/{AuthGroupID}"}, method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> deleteAuthorizationGroup(@PathVariable("AuthGroupID") String ID) throws SQLException{
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			Map<String, Object> m = authService.deleteAuthorizationGroup(dataSource, ID);
			

			if(m.get("MESSAGE") == null || m.get("MESSAGE").equals("")){
				map.put("MESSAGE", "FAILED");
				map.put("STATUS", HttpStatus.NOT_FOUND.value());
				return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			}else{
				map.put("MESSAGE", "SUCCESS");
				map.put("STATUS", HttpStatus.OK.value());
				return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			}
			
		
			
		}catch (Exception e) {
			map.put("message", "fail");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = {"/get/{AuthGroupID}"}, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAuthorizationGroup(@PathVariable("AuthGroupID") String ID) throws SQLException{
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			map = authService.getAuthorizationGroup(ID, dataSource);
			if(map.get("MESSAGE") == null || map.get("MESSAGE").equals("FAIL")){
				map.put("MESSAGE", "FAILED");
				map.put("STATUS", HttpStatus.NOT_FOUND.value());
				return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			}else{
				map.put("MESSAGE", "SUCCESS");
				map.put("STATUS", HttpStatus.OK.value());
				return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			}
			
			
		}catch (Exception e) {
			map.put("message", "fail");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		
	}
	
}
