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

import balancika.ame.entities.Authorization;
import balancika.ame.entities.MeDataSource;
import balancika.ame.service.AuthorizationService;


@RestController
@RequestMapping("/rest/authorization/")
public class AuthorizationController {
	
	@Autowired
	private MeDataSource dataSource;
	
	@Autowired
	private AuthorizationService authService;
	
	@RequestMapping(value = {"/list"}, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listAuthorizationGroup(HttpServletRequest req) throws SQLException{
		Map<String, Object> map = new HashMap<String, Object>();
		dataSource = dataSource.getMeDataSourceByHttpServlet(req);
		
		List<Authorization> auth = authService.listAuthorization(dataSource);
		
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

}
