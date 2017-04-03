package balancika.ame.controller.mobile_pos;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.Connection;

import balancika.ame.entities.MeDataSource;
import balancika.ame.utilities.DBConnection;

@RestController
@RequestMapping("/api/pos/station/")
public class StationController {
	
	@RequestMapping(value = {"/startup"}, method = RequestMethod.POST)
	@ResponseBody
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
	
	
}
