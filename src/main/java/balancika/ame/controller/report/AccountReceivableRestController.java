package balancika.ame.controller.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import balancika.ame.entities.MeDataSource;
import balancika.ame.service.report.AccountReceivableService;

@RestController
@RequestMapping("/rest/account-receivable/")
public class AccountReceivableRestController {
	
	@Autowired
	private MeDataSource dataSource;  
	
	@Autowired
	private AccountReceivableService arService;
	
	@RequestMapping(value = {"/start-up/invoice-register"}, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listTransaction(HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		dataSource = dataSource.getMeDataSourceByHttpServlet(req);
		Map<String, Object> obj = arService.startupInvoiceRegister(dataSource);	
		if(obj != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", obj);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	@RequestMapping(value = {"/invoice-register/summary"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> invoiceRegisterSummary(@RequestBody Map<String, String> criteria,HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>(); 
		dataSource = dataSource.getMeDataSourceByHttpServlet(req);
		List<Map<String, Object>> obj = arService.invoiceRegisterSummary(criteria, dataSource);
		
		if(obj != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", obj);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
}
