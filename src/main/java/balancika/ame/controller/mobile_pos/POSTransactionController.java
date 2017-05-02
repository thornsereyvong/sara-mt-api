package balancika.ame.controller.mobile_pos;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import balancika.ame.service.pos.POSTransactionService;
import balancika.ame.utilities.POSForm;

@RestController
@RequestMapping("/api/pos/transaction/")
public class POSTransactionController {
	
	@Autowired 
	private POSTransactionService posService;
	
	@RequestMapping(value = {"/sale-order/add"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> startup_item(@RequestBody POSForm frm){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean insertStatus = posService.addSaleOrder(frm);
		if(insertStatus){
			map.put("MESSAGE", "SUCCESS");
			map.put("MSG", "");
			map.put("DATA", null);
		}else{
			map.put("MESSAGE", "FAILED");
			map.put("MSG", "");
			map.put("DATA", null);
		}		
		map.put("STATUS", HttpStatus.OK.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
}
