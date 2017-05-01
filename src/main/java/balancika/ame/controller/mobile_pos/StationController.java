package balancika.ame.controller.mobile_pos;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import balancika.ame.entities.MeDataSource;
import balancika.ame.service.pos.StationGroupService;

@RestController
@RequestMapping("/api/pos/station/")
public class StationController {
	
	@Autowired
	private StationGroupService sgService;
	
	@RequestMapping(value = {"/startup"}, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> confServer(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> sgMap = sgService.listStationGroup(dataSource);
		if(sgMap != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("MSG", "");
			map.put("DATA", sgMap);
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("MSG", "");
			map.put("DATA", null);
		}		
		map.put("STATUS", HttpStatus.OK.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/station-group/{stationId}"}, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listStationByStationGroup(@PathVariable("stationId") String stationId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> sgList = sgService.listStationByStationGroup(stationId, dataSource);
		if(sgList != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("MSG", "");
			map.put("DATA", sgList);
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("MSG", "");
			map.put("DATA", null);
		}		
		map.put("STATUS", HttpStatus.OK.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
}
