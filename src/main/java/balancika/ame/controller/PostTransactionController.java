package balancika.ame.controller;

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
import balancika.ame.entities.tansaction.Transaction;
import balancika.ame.service.PostTransactionService;

@RestController
@RequestMapping("/rest/post-transaction/")
public class PostTransactionController {
	
	@Autowired
	private MeDataSource dataSource; 
	
	@Autowired
	private PostTransactionService post;
	
	@RequestMapping(value = {"/list"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> listTransaction(@RequestBody Transaction tran,HttpServletRequest req){
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
	public ResponseEntity<Map<String, Object>> startUp(HttpServletRequest req){
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
	
	@RequestMapping(value = {"/post"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> postTrans(@RequestBody Transaction tran,HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		dataSource = dataSource.getMeDataSourceByHttpServlet(req);
		
		if(tran != null){
			String sql = "";
			switch(tran.getTransType()){  
				case "AP Invoice":
					sql = "SELECT COUNT(*) as CRow FROM tblPurchase WHERE PostStatus = 'Open' AND PurID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spPost_Purchase(?)}";
							if(post.postTransByTransId(sql, tran.getTransId(), dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The purchase with record ID: "+tran.getTransId()+" was successful posted!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The purchase with record ID: "+tran.getTransId()+" was unsuccessful posted!");
							}								
							
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The purchase with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The purchase with record ID: "+tran.getTransId()+" does not exist.");
					}					
					break;
				case "AP Return Invoice": 
					sql = "SELECT COUNT(*) as CRow FROM tblPurchase_Return WHERE PostStatus = 'Open' AND RetID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spPost_PurchaseReturn(?)}";
							if(post.postTransByTransId(sql, tran.getTransId(), dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The purchase return with record ID: "+tran.getTransId()+" was successful posted!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The purchase return with record ID: "+tran.getTransId()+" was unsuccessful posted!");
							}							
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The purchase return with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The purchase return with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
			    case "AP Debit Note":
			    	sql = "SELECT COUNT(*) as CRow FROM tblap_drnote WHERE PostStatus = 'Open' AND DrID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spPost_DebitNote(?)}";
							if(post.postTransByTransId(sql, tran.getTransId(), dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The debit note return with record ID: "+tran.getTransId()+" was successful posted!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The debit note return with record ID: "+tran.getTransId()+" was unsuccessful posted!");
							}	
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The debit note with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The debit note with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
			    case "AP Payment":
			    	sql = "SELECT COUNT(*) as CRow FROM tblPayment WHERE PostStatus = 'Open' AND PmtID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spPost_Payment(?)}";
							if(post.postTransByTransId(sql, tran.getTransId(), dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The payment with record ID: "+tran.getTransId()+" was successful posted!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The payment with record ID: "+tran.getTransId()+" was unsuccessful posted!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The payment with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The payment with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
		    	case "AR Invoice":
		    		sql = "SELECT COUNT(*) as CRow FROM tblSales WHERE PostStatus = 'Open' AND SalID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spPost_Sale(?)}";
							if(post.postTransByTransId(sql, tran.getTransId(), dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The sale with record ID: "+tran.getTransId()+" was successful posted!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The sale with record ID: "+tran.getTransId()+" was unsuccessful posted!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The sale with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The sale with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
	    		case "AR Return Invoice":
	    			sql = "SELECT COUNT(*) as CRow FROM tblSales_Return WHERE PostStatus = 'Open' AND RetID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spPost_SaleReturn(?)}";
							if(post.postTransByTransId(sql, tran.getTransId(), dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The sale return with record ID: "+tran.getTransId()+" was successful posted!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The sale return with record ID: "+tran.getTransId()+" was unsuccessful posted!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The sale return with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The sale return with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
	    		case "AR Credit Note":
	    			sql = "SELECT COUNT(*) as CRow FROM tblCrNote WHERE PostStatus = 'Open' AND CrID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spPost_CreditNote(?)}";
							if(post.postTransByTransId(sql, tran.getTransId(), dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The credit note with record ID: "+tran.getTransId()+" was successful posted!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The credit note with record ID: "+tran.getTransId()+" was unsuccessful posted!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The credit note with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The credit note with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "AR Receipt":
					sql = "SELECT COUNT(*) as CRow FROM tblReceipt WHERE PostStatus = 'Open' AND RcpID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spPost_Receipt(?)}";
							if(post.postTransByTransId(sql, tran.getTransId(), dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The receipt with record ID: "+tran.getTransId()+" was successful posted!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The receipt with record ID: "+tran.getTransId()+" was unsuccessful posted!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The receipt with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The receipt with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "IC Transfer":
					sql = "SELECT COUNT(*) as CRow FROM tblTransfer WHERE PostStatus = 'Open' AND TrfID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spPost_Transfer(?)}";
							if(post.postTransByTransId(sql, tran.getTransId(), dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The transfer with record ID: "+tran.getTransId()+" was successful posted!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The transfer with record ID: "+tran.getTransId()+" was unsuccessful posted!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The transfer with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The transfer with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "IC Internal Usage":
					sql = "SELECT COUNT(*) as CRow FROM tblIntUsage WHERE PostStatus = 'Open' AND IntID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spPost_IntUsage(?)}";
							if(post.postTransByTransId(sql, tran.getTransId(), dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The internal usage with record ID: "+tran.getTransId()+" was successful posted!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The internal usage with record ID: "+tran.getTransId()+" was unsuccessful posted!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The internal usage with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The internal usage with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "IC Adjustment":
					sql = "SELECT COUNT(*) as CRow FROM tblAdjustment WHERE PostStatus = 'Open' AND AdjID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spPost_Adjustment(?)}";
							if(post.postTransByTransId(sql, tran.getTransId(), dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The adjustment with record ID: "+tran.getTransId()+" was successful posted!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The adjustment with record ID: "+tran.getTransId()+" was unsuccessful posted!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The adjustment with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The adjustment with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "Cash Transfer":
					sql = "SELECT COUNT(*) as CRow FROM tblMoney_Company_Transfer WHERE PostStatus = 'Open' AND TrID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spVoid_IntUsage(?)}";
							if(post.postTransByTransId(sql, tran.getTransId(), dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The cash transfer with record ID: "+tran.getTransId()+" was successful posted!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The cash transfer with record ID: "+tran.getTransId()+" was unsuccessful posted!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The cash transfer with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The cash transfer with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "Cash Advance":
					sql = "SELECT COUNT(*) as CRow FROM tblCashAdvance WHERE PostStatus = 'Open' AND CaID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spPost_CashAdvance(?)}";
							if(post.postTransByTransId(sql, tran.getTransId(), dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The cash advance with record ID: "+tran.getTransId()+" was successful posted!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The cash advance with record ID: "+tran.getTransId()+" was unsuccessful posted!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The cash advance with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The cash advance with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "Cash Advance Clearance":
					sql = "SELECT COUNT(*) as CRow FROM tblClearance_History WHERE PostStatus = 'Open' AND ClID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spPost_CashAdvanceClearance(?)}";
							if(post.postTransByTransId(sql, tran.getTransId(), dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The cash advance clearance with record ID: "+tran.getTransId()+" was successful posted!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The cash advance clearance with record ID: "+tran.getTransId()+" was unsuccessful posted!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The cash advance clearance with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The cash advance clearance with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "GL Entries":
					sql = "SELECT COUNT(*) as CRow FROM tblJournal WHERE PostStatus = 'Open' AND JID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){							
							if(post.checkDrCr(tran, dataSource) && post.checkPostJournal(tran, dataSource) && post.checkLine(tran, dataSource)){
								sql = "{ call spPost_Journal(?)}";
								if(post.postTransByTransId(sql, tran.getTransId(), dataSource)){
									map.put("MESSAGE", "SUCCESS");
									map.put("MSG", "The journal entry with record ID: "+tran.getTransId()+" was successful posted!");
								}else{
									map.put("MESSAGE", "FAILED");
									map.put("MSG", "The journal entry with record ID: "+tran.getTransId()+" was unsuccessful posted!");
								}
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The journal entry with record ID: "+tran.getTransId()+" is invalid! Please check and try again...");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The journal entry with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The journal entry with record ID: "+tran.getTransId()+" does not exist.");
					}
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
	
	@RequestMapping(value = {"/void"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> voidTrans(@RequestBody Transaction tran,HttpServletRequest req){
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
									map.put("MSG", "The purchase with record ID: "+tran.getTransId()+" was successful voided!");
								}else{
									map.put("MESSAGE", "FAILED");
									map.put("MSG", "The purchase with record ID: "+tran.getTransId()+" was unsuccessful voided!");
								}								
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", msg);
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The purchase with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The purchase with record ID: "+tran.getTransId()+" does not exist.");
					}					
					break;
				case "AP Return Invoice": 
					sql = "SELECT COUNT(*) as CRow FROM tblPurchase_Return WHERE PostStatus = 'Posted' AND RetID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							String msg = post.checkReference("AP-RE", tran.getTransId(), dataSource);
							if(msg.equals("")){
								sql = "{ call spVoid_PurchaseReturn(?,?)}";
								if(post.voidTransByTransId(sql, tran.getTransId(), 0, dataSource)){
									map.put("MESSAGE", "SUCCESS");
									map.put("MSG", "The purchase return with record ID: "+tran.getTransId()+" was successful voided!");
								}else{
									map.put("MESSAGE", "FAILED");
									map.put("MSG", "The purchase return with record ID: "+tran.getTransId()+" was unsuccessful voided!");
								}								
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", msg);
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The purchase return with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The purchase return with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
			    case "AP Debit Note":
			    	sql = "SELECT COUNT(*) as CRow FROM tblap_drnote WHERE PostStatus = 'Posted' AND DrID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							String msg = post.checkReference("AP-DN", tran.getTransId(), dataSource);
							if(msg.equals("")){
								sql = "{ call spVoid_DebitNote(?,?)}";
								if(post.voidTransByTransId(sql, tran.getTransId(), 0, dataSource)){
									map.put("MESSAGE", "SUCCESS");
									map.put("MSG", "The debit note return with record ID: "+tran.getTransId()+" was successful voided!");
								}else{
									map.put("MESSAGE", "FAILED");
									map.put("MSG", "The debit note return with record ID: "+tran.getTransId()+" was unsuccessful voided!");
								}								
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", msg);
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The debit note with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The debit note with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
			    case "AP Payment":
			    	sql = "SELECT COUNT(*) as CRow FROM tblPayment WHERE PostStatus = 'Posted' AND PmtID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spVoid_Payment(?,?)}";
							if(post.voidTransByTransId(sql, tran.getTransId(), 0, dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The payment with record ID: "+tran.getTransId()+" was successful voided!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The payment with record ID: "+tran.getTransId()+" was unsuccessful voided!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The payment with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The payment with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
		    	case "AR Invoice":
		    		sql = "SELECT COUNT(*) as CRow FROM tblSales WHERE PostStatus = 'Posted' AND SalID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							String msg = post.checkReference("AR-IN", tran.getTransId(), dataSource);
							if(msg.equals("")){
								sql = "{ call spVoid_Sale(?,?)}";
								if(post.voidTransByTransId(sql, tran.getTransId(), 0, dataSource)){
									map.put("MESSAGE", "SUCCESS");
									map.put("MSG", "The sale with record ID: "+tran.getTransId()+" was successful voided!");
								}else{
									map.put("MESSAGE", "FAILED");
									map.put("MSG", "The sale with record ID: "+tran.getTransId()+" was unsuccessful voided!");
								}								
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", msg);
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The sale with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The sale with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
	    		case "AR Return Invoice":
	    			sql = "SELECT COUNT(*) as CRow FROM tblSales_Return WHERE PostStatus = 'Posted' AND RetID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							String msg = post.checkReference("AR-RET", tran.getTransId(), dataSource);
							if(msg.equals("")){
								sql = "{ call spVoid_SaleReturn(?,?)}";
								if(post.voidTransByTransId(sql, tran.getTransId(), 0, dataSource)){
									map.put("MESSAGE", "SUCCESS");
									map.put("MSG", "The sale return with record ID: "+tran.getTransId()+" was successful voided!");
								}else{
									map.put("MESSAGE", "FAILED");
									map.put("MSG", "The sale return with record ID: "+tran.getTransId()+" was unsuccessful voided!");
								}								
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", msg);
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The sale return with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The sale return with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
	    		case "AR Credit Note":
	    			sql = "SELECT COUNT(*) as CRow FROM tblCrNote WHERE PostStatus = 'Posted' AND CrID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							String msg = post.checkReference("AR-CN", tran.getTransId(), dataSource);
							if(msg.equals("")){
								sql = "{ call spVoid_CreditNote(?,?)}";
								if(post.voidTransByTransId(sql, tran.getTransId(), 0, dataSource)){
									map.put("MESSAGE", "SUCCESS");
									map.put("MSG", "The credit note with record ID: "+tran.getTransId()+" was successful voided!");
								}else{
									map.put("MESSAGE", "FAILED");
									map.put("MSG", "The credit note with record ID: "+tran.getTransId()+" was unsuccessful voided!");
								}								
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", msg);
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The credit note with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The credit note with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "AR Receipt":
					sql = "SELECT COUNT(*) as CRow FROM tblReceipt WHERE PostStatus = 'Posted' AND RcpID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spVoid_Receipt(?,?)}";
							if(post.voidTransByTransId(sql, tran.getTransId(), 0, dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The receipt with record ID: "+tran.getTransId()+" was successful voided!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The receipt with record ID: "+tran.getTransId()+" was unsuccessful voided!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The receipt with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The receipt with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "IC Transfer":
					sql = "SELECT COUNT(*) as CRow FROM tblTransfer WHERE PostStatus = 'Posted' AND TrfID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spVoid_Transfer(?,?)}";
							if(post.voidTransByTransId(sql, tran.getTransId(), 0, dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The transfer with record ID: "+tran.getTransId()+" was successful voided!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The transfer with record ID: "+tran.getTransId()+" was unsuccessful voided!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The transfer with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The transfer with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "IC Internal Usage":
					sql = "SELECT COUNT(*) as CRow FROM tblIntUsage WHERE PostStatus = 'Posted' AND IntID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spVoid_IntUsage(?,?)}";
							if(post.voidTransByTransId(sql, tran.getTransId(), 0, dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The internal usage with record ID: "+tran.getTransId()+" was successful voided!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The internal usage with record ID: "+tran.getTransId()+" was unsuccessful voided!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The internal usage with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The internal usage with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;	
				case "IC Adjustment":
					sql = "SELECT COUNT(*) as CRow FROM tblAdjustment WHERE PostStatus = 'Posted' AND AdjID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spVoid_Adjustment(?,?)}";
							if(post.voidTransByTransId(sql, tran.getTransId(), 0, dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The adjustment with record ID: "+tran.getTransId()+" was successful voided!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The adjustment with record ID: "+tran.getTransId()+" was unsuccessful voided!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The adjustment with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The adjustment with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;				
				case "Cash Transfer":
					sql = "SELECT COUNT(*) as CRow FROM tblMoney_Company_Transfer WHERE PostStatus = 'Posted' AND TrID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spVoid_IntUsage(?,?)}";
							if(post.voidTransByTransId(sql, tran.getTransId(), 0, dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The cash transfer with record ID: "+tran.getTransId()+" was successful voided!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The cash transfer with record ID: "+tran.getTransId()+" was unsuccessful voided!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The cash transfer with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The cash transfer with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "Cash Advance":
					sql = "SELECT COUNT(*) as CRow FROM tblCashAdvance WHERE PostStatus = 'Posted' AND CaID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							String msg = post.checkReference("AP-CAR", tran.getTransId(), dataSource);
							if(msg.equals("")){
								sql = "{ call spVoid_CashAdvance(?,?)}";
								if(post.voidTransByTransId(sql, tran.getTransId(), 0, dataSource)){
									map.put("MESSAGE", "SUCCESS");
									map.put("MSG", "The cash advance with record ID: "+tran.getTransId()+" was successful voided!");
								}else{
									map.put("MESSAGE", "FAILED");
									map.put("MSG", "The cash advance with record ID: "+tran.getTransId()+" was unsuccessful voided!");
								}								
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", msg);
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The cash advance with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The cash advance with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "Cash Advance Clearance":
					sql = "SELECT COUNT(*) as CRow FROM tblClearance_History WHERE PostStatus = 'Posted' AND ClID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spVoid_CashAdvanceClearance(?,?)}";
							if(post.voidTransByTransId(sql, tran.getTransId(), 0, dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The cash advance clearance with record ID: "+tran.getTransId()+" was successful voided!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The cash advance clearance with record ID: "+tran.getTransId()+" was unsuccessful voided!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The cash advance clearance with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The cash advance clearance with record ID: "+tran.getTransId()+" does not exist.");
					}
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
	
	@RequestMapping(value = {"/void-and-clone"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> voidAndCloneTrans(@RequestBody Transaction tran,HttpServletRequest req){
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
								if(post.voidTransByTransId(sql, tran.getTransId(), 1, dataSource)){
									map.put("MESSAGE", "SUCCESS");
									map.put("MSG", "The purchase with record ID: "+tran.getTransId()+" was successful voided!");
								}else{
									map.put("MESSAGE", "FAILED");
									map.put("MSG", "The purchase with record ID: "+tran.getTransId()+" was unsuccessful voided!");
								}								
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", msg);
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The purchase with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The purchase with record ID: "+tran.getTransId()+" does not exist.");
					}					
					break;
				case "AP Return Invoice": 
					sql = "SELECT COUNT(*) as CRow FROM tblPurchase_Return WHERE PostStatus = 'Posted' AND RetID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							String msg = post.checkReference("AP-RE", tran.getTransId(), dataSource);
							if(msg.equals("")){
								sql = "{ call spVoid_PurchaseReturn(?,?)}";
								if(post.voidTransByTransId(sql, tran.getTransId(), 1, dataSource)){
									map.put("MESSAGE", "SUCCESS");
									map.put("MSG", "The purchase return with record ID: "+tran.getTransId()+" was successful voided!");
								}else{
									map.put("MESSAGE", "FAILED");
									map.put("MSG", "The purchase return with record ID: "+tran.getTransId()+" was unsuccessful voided!");
								}								
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", msg);
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The purchase return with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The purchase return with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
			    case "AP Debit Note":
			    	sql = "SELECT COUNT(*) as CRow FROM tblap_drnote WHERE PostStatus = 'Posted' AND DrID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							String msg = post.checkReference("AP-DN", tran.getTransId(), dataSource);
							if(msg.equals("")){
								sql = "{ call spVoid_DebitNote(?,?)}";
								if(post.voidTransByTransId(sql, tran.getTransId(), 1, dataSource)){
									map.put("MESSAGE", "SUCCESS");
									map.put("MSG", "The debit note return with record ID: "+tran.getTransId()+" was successful voided!");
								}else{
									map.put("MESSAGE", "FAILED");
									map.put("MSG", "The debit note return with record ID: "+tran.getTransId()+" was unsuccessful voided!");
								}								
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", msg);
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The debit note with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The debit note with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
			    case "AP Payment":
			    	sql = "SELECT COUNT(*) as CRow FROM tblPayment WHERE PostStatus = 'Posted' AND PmtID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spVoid_Payment(?,?)}";
							if(post.voidTransByTransId(sql, tran.getTransId(), 1, dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The payment with record ID: "+tran.getTransId()+" was successful voided!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The payment with record ID: "+tran.getTransId()+" was unsuccessful voided!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The payment with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The payment with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
		    	case "AR Invoice":
		    		sql = "SELECT COUNT(*) as CRow FROM tblSales WHERE PostStatus = 'Posted' AND SalID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							String msg = post.checkReference("AR-IN", tran.getTransId(), dataSource);
							if(msg.equals("")){
								sql = "{ call spVoid_Sale(?,?)}";
								if(post.voidTransByTransId(sql, tran.getTransId(), 1, dataSource)){
									map.put("MESSAGE", "SUCCESS");
									map.put("MSG", "The sale with record ID: "+tran.getTransId()+" was successful voided!");
								}else{
									map.put("MESSAGE", "FAILED");
									map.put("MSG", "The sale with record ID: "+tran.getTransId()+" was unsuccessful voided!");
								}								
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", msg);
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The sale with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The sale with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
	    		case "AR Return Invoice":
	    			sql = "SELECT COUNT(*) as CRow FROM tblSales_Return WHERE PostStatus = 'Posted' AND RetID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							String msg = post.checkReference("AR-RET", tran.getTransId(), dataSource);
							if(msg.equals("")){
								sql = "{ call spVoid_SaleReturn(?,?)}";
								if(post.voidTransByTransId(sql, tran.getTransId(), 1, dataSource)){
									map.put("MESSAGE", "SUCCESS");
									map.put("MSG", "The sale return with record ID: "+tran.getTransId()+" was successful voided!");
								}else{
									map.put("MESSAGE", "FAILED");
									map.put("MSG", "The sale return with record ID: "+tran.getTransId()+" was unsuccessful voided!");
								}								
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", msg);
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The sale return with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The sale return with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
	    		case "AR Credit Note":
	    			sql = "SELECT COUNT(*) as CRow FROM tblCrNote WHERE PostStatus = 'Posted' AND CrID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							String msg = post.checkReference("AR-CN", tran.getTransId(), dataSource);
							if(msg.equals("")){
								sql = "{ call spVoid_CreditNote(?,?)}";
								if(post.voidTransByTransId(sql, tran.getTransId(), 1, dataSource)){
									map.put("MESSAGE", "SUCCESS");
									map.put("MSG", "The credit note with record ID: "+tran.getTransId()+" was successful voided!");
								}else{
									map.put("MESSAGE", "FAILED");
									map.put("MSG", "The credit note with record ID: "+tran.getTransId()+" was unsuccessful voided!");
								}								
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", msg);
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The credit note with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The credit note with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "AR Receipt":
					sql = "SELECT COUNT(*) as CRow FROM tblReceipt WHERE PostStatus = 'Posted' AND RcpID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spVoid_Receipt(?,?)}";
							if(post.voidTransByTransId(sql, tran.getTransId(), 1, dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The receipt with record ID: "+tran.getTransId()+" was successful voided!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The receipt with record ID: "+tran.getTransId()+" was unsuccessful voided!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The receipt with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The receipt with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "IC Transfer":
					sql = "SELECT COUNT(*) as CRow FROM tblTransfer WHERE PostStatus = 'Posted' AND TrfID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spVoid_Transfer(?,?)}";
							if(post.voidTransByTransId(sql, tran.getTransId(), 1, dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The transfer with record ID: "+tran.getTransId()+" was successful voided!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The transfer with record ID: "+tran.getTransId()+" was unsuccessful voided!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The transfer with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The transfer with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "IC Internal Usage":
					sql = "SELECT COUNT(*) as CRow FROM tblIntUsage WHERE PostStatus = 'Posted' AND IntID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spVoid_IntUsage(?,?)}";
							if(post.voidTransByTransId(sql, tran.getTransId(), 1, dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The internal usage with record ID: "+tran.getTransId()+" was successful voided!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The internal usage with record ID: "+tran.getTransId()+" was unsuccessful voided!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The internal usage with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The internal usage with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;	
				case "IC Adjustment":
					sql = "SELECT COUNT(*) as CRow FROM tblAdjustment WHERE PostStatus = 'Posted' AND AdjID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spVoid_Adjustment(?,?)}";
							if(post.voidTransByTransId(sql, tran.getTransId(), 1, dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The adjustment with record ID: "+tran.getTransId()+" was successful voided!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The adjustment with record ID: "+tran.getTransId()+" was unsuccessful voided!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The adjustment with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The adjustment with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;				
				case "Cash Transfer":
					sql = "SELECT COUNT(*) as CRow FROM tblMoney_Company_Transfer WHERE PostStatus = 'Posted' AND TrID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spVoid_IntUsage(?,?)}";
							if(post.voidTransByTransId(sql, tran.getTransId(), 1, dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The cash transfer with record ID: "+tran.getTransId()+" was successful voided!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The cash transfer with record ID: "+tran.getTransId()+" was unsuccessful voided!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The cash transfer with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The cash transfer with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "Cash Advance":
					sql = "SELECT COUNT(*) as CRow FROM tblCashAdvance WHERE PostStatus = 'Posted' AND CaID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							String msg = post.checkReference("AP-CAR", tran.getTransId(), dataSource);
							if(msg.equals("")){
								sql = "{ call spVoid_CashAdvance(?,?)}";
								if(post.voidTransByTransId(sql, tran.getTransId(), 1, dataSource)){
									map.put("MESSAGE", "SUCCESS");
									map.put("MSG", "The cash advance with record ID: "+tran.getTransId()+" was successful voided!");
								}else{
									map.put("MESSAGE", "FAILED");
									map.put("MSG", "The cash advance with record ID: "+tran.getTransId()+" was unsuccessful voided!");
								}								
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", msg);
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The cash advance with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The cash advance with record ID: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "Cash Advance Clearance":
					sql = "SELECT COUNT(*) as CRow FROM tblClearance_History WHERE PostStatus = 'Posted' AND ClID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						if(!post.checkLockPeriod(tran, dataSource)){
							sql = "{ call spVoid_CashAdvanceClearance(?,?)}";
							if(post.voidTransByTransId(sql, tran.getTransId(), 1, dataSource)){
								map.put("MESSAGE", "SUCCESS");
								map.put("MSG", "The cash advance clearance with record ID: "+tran.getTransId()+" was successful voided!");
							}else{
								map.put("MESSAGE", "FAILED");
								map.put("MSG", "The cash advance clearance with record ID: "+tran.getTransId()+" was unsuccessful voided!");
							}
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The cash advance clearance with record id: "+tran.getTransId()+" in date: '"+tran.getTransDate()+"' was locked. the system will continue automatically.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The cash advance clearance with record ID: "+tran.getTransId()+" does not exist.");
					}
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
	
	@RequestMapping(value = {"/list-by-id"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> listById(@RequestBody Transaction tran,HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		dataSource = dataSource.getMeDataSourceByHttpServlet(req);
		
		if(tran != null){
			String sql = "";
			switch(tran.getTransType()){  
				case "AP Invoice":
					sql = "SELECT COUNT(*) as CRow FROM tblPurchase WHERE PurID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						map.put("MESSAGE", "SUCCESS");
						
						
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The purchase with record id: "+tran.getTransId()+" does not exist.");
					}					
					break;
				case "AP Return Invoice": 
					sql = "SELECT COUNT(*) as CRow FROM tblPurchase_Return WHERE RetID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						map.put("MESSAGE", "SUCCESS");
						
						
					}else{
						map.put("MESSAGE", "FAILED");
						
					}
			    	break;
			    case "AP Debit Note":
			    	sql = "SELECT COUNT(*) as CRow FROM tblap_drnote WHERE DrID = '"+tran.getTransId()+"'";
			    	if(post.checkExist(sql, dataSource)){
						map.put("MESSAGE", "SUCCESS");
						
						
					}else{
						map.put("MESSAGE", "FAILED");
						
					}
			    	break;
			    case "AP Payment":
			    	sql = "SELECT COUNT(*) as CRow FROM tblPayment WHERE PmtID = '"+tran.getTransId()+"'";
			    	if(post.checkExist(sql, dataSource)){
						map.put("MESSAGE", "SUCCESS");
						
						
					}else{
						map.put("MESSAGE", "FAILED");
						
					}
			    	break;
		    	case "AR Invoice":
		    		sql = "SELECT COUNT(*) as CRow FROM tblSales WHERE SalID = '"+tran.getTransId()+"'";
		    		if(post.checkExist(sql, dataSource)){
						map.put("MESSAGE", "SUCCESS");
						
						
					}else{
						map.put("MESSAGE", "FAILED");
						
					}
			    	break;
	    		case "AR Return Invoice":
	    			sql = "SELECT COUNT(*) as CRow FROM tblSales_Return WHERE RetID = '"+tran.getTransId()+"'";
	    			if(post.checkExist(sql, dataSource)){
						map.put("MESSAGE", "SUCCESS");
						
						
					}else{
						map.put("MESSAGE", "FAILED");
						
					}
			    	break;
	    		case "AR Credit Note":
	    			sql = "SELECT COUNT(*) as CRow FROM tblCrNote WHERE CrID = '"+tran.getTransId()+"'";
	    			if(post.checkExist(sql, dataSource)){
						map.put("MESSAGE", "SUCCESS");
						
						
					}else{
						map.put("MESSAGE", "FAILED");
						
					}
			    	break;
				case "AR Receipt":
					sql = "SELECT COUNT(*) as CRow FROM tblReceipt WHERE RcpID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						map.put("MESSAGE", "SUCCESS");
						
						
					}else{
						map.put("MESSAGE", "FAILED");
						
					}
			    	break;
				case "IC Transfer":
					sql = "SELECT COUNT(*) as CRow FROM tblTransfer WHERE TrfID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						map.put("MESSAGE", "SUCCESS");
						
						
					}else{
						map.put("MESSAGE", "FAILED");
						
					}
			    	break;
				case "IC Internal Usage":
					sql = "SELECT COUNT(*) as CRow FROM tblIntUsage WHERE IntID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						map.put("MESSAGE", "SUCCESS");
						
						
					}else{
						map.put("MESSAGE", "FAILED");
						
					}
			    	break;
				case "IC Adjustment":
					sql = "SELECT COUNT(*) as CRow FROM tblAdjustment WHERE AdjID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						map.put("MESSAGE", "SUCCESS");
						
						
					}else{
						map.put("MESSAGE", "FAILED");
						
					}
			    	break;
				case "Cash Transfer":
					sql = "SELECT COUNT(*) as CRow FROM tblMoney_Company_Transfer WHERE TrID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						map.put("MESSAGE", "SUCCESS");
						
						
					}else{
						map.put("MESSAGE", "FAILED");
						
					}
			    	break;
				case "Cash Advance":
					sql = "SELECT COUNT(*) as CRow FROM tblCashAdvance WHERE CaID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						map.put("MESSAGE", "SUCCESS");
						
						
					}else{
						map.put("MESSAGE", "FAILED");
						
					}
			    	break;
				case "Cash Advance Clearance":
					sql = "SELECT COUNT(*) as CRow FROM tblClearance_History WHERE ClID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						map.put("MESSAGE", "SUCCESS");
						
						
					}else{
						map.put("MESSAGE", "FAILED");
						
					}
			    	break;
				case "GL Entries":
					sql = "SELECT COUNT(*) as CRow FROM tblJournal WHERE JID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						map.put("MESSAGE", "SUCCESS");
						
						
					}else{
						map.put("MESSAGE", "FAILED");
						
					}
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
