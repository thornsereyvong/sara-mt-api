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
import balancika.ame.service.pos.ItemGroupService;
import balancika.ame.service.pos.ItemService;

@RestController
@RequestMapping("/api/pos/item/")
public class ItemController {
	
	@Autowired
	private ItemGroupService igService;
	
	@Autowired
	private ItemService iService;
	
	@RequestMapping(value = {"/startup/{filter}"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> startup_item(@PathVariable("filter") String filter,@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> igMap = igService.listItemGroupGroup(filter, dataSource);
		if(igMap != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("MSG", "");
			map.put("DATA", igMap);
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("MSG", "");
			map.put("DATA", null);
		}		
		map.put("STATUS", HttpStatus.OK.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/detail/{filter}"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getItemDetail(@PathVariable("filter") String filter,@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> iMap = iService.listItemDetail(filter, dataSource);
		if(iMap != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("MSG", "");
			map.put("DATA", iMap);
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("MSG", "");
			map.put("DATA", null);
		}		
		map.put("STATUS", HttpStatus.OK.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
}
