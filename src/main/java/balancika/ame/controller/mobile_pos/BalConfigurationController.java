package balancika.ame.controller.mobile_pos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mysql.jdbc.Connection;
import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.SDCompany;
import balancika.ame.service.DatabaseService;
import balancika.ame.utilities.DBConnection;

@RestController
@RequestMapping("/api/pos/configuration/")
public class BalConfigurationController {	
	
	@Autowired
	private DatabaseService dbService;
	
	@RequestMapping(value = {"/server"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> confServer(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		Connection con = DBConnection.getConnection(dataSource);		
		if(con != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("MSG", "Config server successully!");
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("MSG", "Invalid server!");
		}		
		map.put("STATUS", HttpStatus.OK.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/company"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getCompany(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();		
		dataSource.setDb("systemdatabase");
		List<SDCompany> coms = dbService.listAllSystemDatabase(dataSource);		
		if(coms != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("MSG", "");
			map.put("DATA", coms);
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("MSG", "");
			map.put("DATA", null);
		}		
		map.put("STATUS", HttpStatus.OK.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/database"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getDatabase(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();		
		dataSource.setDb("systemdatabase");
		List<Map<String, Object>> coms = dbService.showAllDatabase(dataSource);	
		if(coms != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("MSG", "");
			map.put("DATA", coms);
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("MSG", "");
			map.put("DATA", null);
		}		
		map.put("STATUS", HttpStatus.OK.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/default"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getDefault(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();		
		Map<String, Object> coms = dbService.getDefaultConfig(dataSource);		
		if(coms != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("MSG", "");
			map.put("DATA", coms);
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("MSG", "");
			map.put("DATA", null);
		}		
		map.put("STATUS", HttpStatus.OK.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	
}
