package balancika.ame.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.tansaction.PurchaseInvoice;
import balancika.ame.service.PurchaseInvoiceService;

@RestController
@RequestMapping("/rest/purchase-invoice/")
public class PurchaseInvoiceController {
	@Autowired
	private MeDataSource dataSource; 
	
	@Autowired
	private PurchaseInvoiceService purService;
	
	@RequestMapping(value = {"/list/{purchaseId}"}, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPurchaseInvoiceById(@PathVariable("purchaseId") String purchaseId,HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		dataSource = dataSource.getMeDataSourceByHttpServlet(req);
		PurchaseInvoice pur = new PurchaseInvoice();
		pur.setPurchaseId(purchaseId);
		PurchaseInvoice purchase = purService.getPurchaseInvoice(pur, dataSource);
		if(purchase != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", purchase);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
}
