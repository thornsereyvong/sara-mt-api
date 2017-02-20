package balancika.ame.controller;

import java.sql.SQLException;
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
import balancika.ame.entities.PostTransactionFrm;
import balancika.ame.entities.Transaction;
import balancika.ame.service.PostTransactionService;

@RestController
@RequestMapping("/rest/post-transaction/")
public class PostTransactionController {
	
	@Autowired
	private MeDataSource dataSource; 
	
	@Autowired
	private PostTransactionService post;
	
	
	
	@RequestMapping(value = {"/list"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> listTransaction(@RequestBody Transaction tran,HttpServletRequest req) throws SQLException{
		Map<String, Object> map = new HashMap<String, Object>();
		dataSource = dataSource.getMeDataSourceByHttpServlet(req);
		List<Transaction> trans = 	post.listTransaction(tran, dataSource);
				
		if(trans != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", trans);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	@RequestMapping(value = {"/start-up"}, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> startUp(HttpServletRequest req) throws SQLException{
		Map<String, Object> map = new HashMap<String, Object>();
		dataSource = dataSource.getMeDataSourceByHttpServlet(req);
		List<Transaction> trans = 	post.listTransFTDate(dataSource);
				
		if(trans != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", trans);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	@RequestMapping(value = {"/post"}, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> postTrans(@RequestBody PostTransactionFrm trans,HttpServletRequest req) throws SQLException{
		Map<String, Object> map = new HashMap<String, Object>();
		dataSource = dataSource.getMeDataSourceByHttpServlet(req);
		//List<Transaction> trans = 	post.listTransFTDate(dataSource);
		
		if(trans != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", trans);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/void"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> voidTrans(@RequestBody Transaction tran,HttpServletRequest req) throws SQLException{
		Map<String, Object> map = new HashMap<String, Object>();
		dataSource = dataSource.getMeDataSourceByHttpServlet(req);
		if(tran != null){
			String sql = "";
			switch(tran.getTransType()){  
				case "AP Invoice":
					sql = "SELECT COUNT(*) as CRow FROM tblPurchase WHERE PostStatus = 'Posted' AND PurID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							String msg = post.checkReference("AP-IN", tran.getTransId(), dataSource);
							if(msg.equals("")){
								sql = "{ call spVoid_Purchase(?,?)}";
								if(post.voidTransByTransId(sql, tran.getTransId(), 0, dataSource)){
									map.put("MESSAGE", "SUCCESS");
									map.put("MSG", "The purchase with record ID: "+tran.getTransId()+"was successful voided!");
								}else{
									map.put("MESSAGE", "FAILED");
									map.put("MSG", "The purchase with record ID: "+tran.getTransId()+"was unsuccessful voided!");
								}								
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", msg);
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The purchase with record ID: "+tran.getTransId()+" was locked.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The purchase with record ID: "+tran.getTransId()+" does not exist.");
					}					
					break;
				case "AP Return Invoice": 
			    	
			    	break;
			    case "AP Debit Note":
			    	
			    	break;
			    case "AP Payment":
			    	
			    	break;
		    	case "AR Invoice":
		    		
			    	break;
	    		case "AR Return Invoice":
	    			
			    	break;
	    		case "AR Credit Note":
	    			
			    	break;
				case "AR Receipt":
					
			    	break;
				case "IC Transfer":
					
			    	break;
				case "IC Adjustment":
					
			    	break;
				case "Cash Transfer":
					//sql = "SELECT COUNT(*) 'Exist' FROM tblMoney_Company_Transfer WHERE PostStatus = 'Posted' and TrID=?";
			    	break;
				case "Cash Advance Clearance":
					
			    	break;
				case "GL Entries":
					
			    	break;
			    default:
		    	
			} 
			
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	
	
}
