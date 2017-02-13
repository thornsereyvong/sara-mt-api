package balancika.ame.controller;

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

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.SDCompany;
import balancika.ame.service.DatabaseService;




@RestController
@RequestMapping("/rest/database/")
public class SystemDatabaseController {
	
	@Autowired
	private DatabaseService dbService;
	
	@RequestMapping(value = {"/list"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> listCompany(HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		MeDataSource dataSource  = new MeDataSource();
		dataSource.setIp(req.getSession().getAttribute("ip").toString());
		dataSource.setPort(req.getSession().getAttribute("port").toString());
		dataSource.setUn(req.getSession().getAttribute("usernamedb").toString());
		dataSource.setPw(req.getSession().getAttribute("passworddb").toString());
		dataSource.setDb("systemdatabase");
		List<SDCompany> arrCom = dbService.listAllSystemDatabase(dataSource);
		
		if(arrCom != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrCom);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
}
