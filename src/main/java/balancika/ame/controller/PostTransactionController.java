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
import balancika.ame.entities.tansaction.APPayment;
import balancika.ame.entities.tansaction.ARReceipt;
import balancika.ame.entities.tansaction.CashAdvance;
import balancika.ame.entities.tansaction.CashAdvanceClearance;
import balancika.ame.entities.tansaction.CashTransfer;
import balancika.ame.entities.tansaction.CreditNote;
import balancika.ame.entities.tansaction.DebitNote;
import balancika.ame.entities.tansaction.ICAdjustment;
import balancika.ame.entities.tansaction.ICTransfer;
import balancika.ame.entities.tansaction.Journal;
import balancika.ame.entities.tansaction.PurchaseInvoice;
import balancika.ame.entities.tansaction.PurchaseReturn;
import balancika.ame.entities.tansaction.Sale;
import balancika.ame.entities.tansaction.SaleReturn;
import balancika.ame.entities.tansaction.Transaction;
import balancika.ame.service.APPaymentService;
import balancika.ame.service.ARReceiptService;
import balancika.ame.service.CashAdvanceClearanceService;
import balancika.ame.service.CashAdvanceService;
import balancika.ame.service.CashTransferService;
import balancika.ame.service.CreditNoteService;
import balancika.ame.service.DebitNoteService;
import balancika.ame.service.ICAdjustmentService;
import balancika.ame.service.ICTransferService;
import balancika.ame.service.JournalService;
import balancika.ame.service.PostTransactionService;
import balancika.ame.service.PurchaseInvoiceService;
import balancika.ame.service.PurchaseReturnService;
import balancika.ame.service.SaleReturnService;
import balancika.ame.service.SaleService;

@RestController
@RequestMapping("/rest/post-transaction/")
public class PostTransactionController {
	
	@Autowired
	private MeDataSource dataSource; 
	
	@Autowired
	private PostTransactionService post;
	
	@Autowired
	private PurchaseInvoiceService purService;
	
	@Autowired
	private PurchaseReturnService purReturnService;
	
	@Autowired
	private DebitNoteService dnService;
	
	@Autowired
	private APPaymentService apPaymentService;
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private SaleReturnService saleReturnService;
	
	@Autowired
	private CreditNoteService cdnService;
	
	@Autowired
	private ARReceiptService rcpService;
	
	@Autowired
	private ICTransferService trfService;
	
	@Autowired
	private ICAdjustmentService adjService;
	
	@Autowired
	private CashAdvanceService caService;
	
	@Autowired
	private CashTransferService ctfService;
	
	@Autowired
	private JournalService jnService;
	
	@Autowired
	private CashAdvanceClearanceService clService;
	
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
						PurchaseInvoice pur = new PurchaseInvoice();
						pur.setPurchaseId(tran.getTransId());
						PurchaseInvoice purchase = purService.getPurchaseInvoice(pur, dataSource);						
						if(purchase != null){
							map.put("MESSAGE", "SUCCESS");
							map.put("STATUS", HttpStatus.OK.value());
							map.put("DATA", purchase);
							return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The purchase with record id: "+tran.getTransId()+" does not exist.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The purchase with record id: "+tran.getTransId()+" does not exist.");
					}
					break;
				case "AP Return Invoice": 
					sql = "SELECT COUNT(*) as CRow FROM tblPurchase_Return WHERE RetID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						PurchaseReturn pur = new PurchaseReturn();
						pur.setPurchaseReturnId(tran.getTransId());
						PurchaseReturn purchase = purReturnService.getPurchaseInvoice(pur, dataSource);
						if(purchase != null){
							map.put("MESSAGE", "SUCCESS");
							map.put("STATUS", HttpStatus.OK.value());
							map.put("DATA", purchase);
							return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The purchase return with record id: "+tran.getTransId()+" does not exist.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The purchase return with record id: "+tran.getTransId()+" does not exist.");
					}
			    	break;
			    case "AP Debit Note":
			    	sql = "SELECT COUNT(*) as CRow FROM tblap_drnote WHERE DrID = '"+tran.getTransId()+"'";
			    	if(post.checkExist(sql, dataSource)){
						DebitNote dn = new DebitNote();
						dn.setEntryId(tran.getTransId());
						DebitNote dnd = dnService.getDebitNote(dn, dataSource);
						
						if(dnd != null){
							map.put("MESSAGE", "SUCCESS");
							map.put("STATUS", HttpStatus.OK.value());
							map.put("DATA", dnd);
							return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The AP Debit Note with record id: "+tran.getTransId()+" does not exist.");
						}
						
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The AP Debit Note with record id: "+tran.getTransId()+" does not exist.");
					}
			    	break;
			    case "AP Payment":
			    	sql = "SELECT COUNT(*) as CRow FROM tblPayment WHERE PmtID = '"+tran.getTransId()+"'";
			    	if(post.checkExist(sql, dataSource)){
						APPayment apPayment = new APPayment();
						apPayment.setPmtId(tran.getTransId());
						APPayment appay = apPaymentService.getAPPayment(apPayment, dataSource);
						if(appay != null){
							map.put("MESSAGE", "SUCCESS");
							map.put("STATUS", HttpStatus.OK.value());
							map.put("DATA", appay);
							return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The AP Payment with record id: "+tran.getTransId()+" does not exist.");
						}						
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The AP Payment with record id: "+tran.getTransId()+" does not exist.");
					}
			    	break;
		    	case "AR Invoice":
		    		sql = "SELECT COUNT(*) as CRow FROM tblSales WHERE SalID = '"+tran.getTransId()+"'";
		    		if(post.checkExist(sql, dataSource)){
						Sale sale = new Sale();
						sale.setSaleId(tran.getTransId());
						Sale sales = saleService.getSale(sale, dataSource);
		    			if(sales != null){
		    				map.put("MESSAGE", "SUCCESS");
							map.put("STATUS", HttpStatus.OK.value());
							map.put("DATA", sales);
							return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		    			}else{
		    				map.put("MESSAGE", "FAILED");
							map.put("MSG", "The AP Invoice with record id: "+tran.getTransId()+" does not exist.");
		    			}						
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The AP Invoice with record id: "+tran.getTransId()+" does not exist.");
					}
			    	break;
	    		case "AR Return Invoice":
	    			sql = "SELECT COUNT(*) as CRow FROM tblSales_Return WHERE RetID = '"+tran.getTransId()+"'";
	    			if(post.checkExist(sql, dataSource)){
						SaleReturn sr = new SaleReturn();
						sr.setSaleReturnId(tran.getTransId());
						SaleReturn sale = saleReturnService.getSaleReturn(sr, dataSource);
						if(sale != null){
		    				map.put("MESSAGE", "SUCCESS");
							map.put("STATUS", HttpStatus.OK.value());
							map.put("DATA", sale);
							return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		    			}else{
		    				map.put("MESSAGE", "FAILED");
							map.put("MSG", "The AP Invoice Return with record id: "+tran.getTransId()+" does not exist.");
		    			}						
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The AP Invoice Return with record id: "+tran.getTransId()+" does not exist.");
					}
			    	break;
	    		case "AR Credit Note":
	    			sql = "SELECT COUNT(*) as CRow FROM tblCrNote WHERE CrID = '"+tran.getTransId()+"'";
	    			if(post.checkExist(sql, dataSource)){
						CreditNote cdn = new CreditNote();
						cdn.setEntryId(tran.getTransId());
						CreditNote credit = cdnService.getCreditNote(cdn, dataSource);
						if(credit != null){
		    				map.put("MESSAGE", "SUCCESS");
							map.put("STATUS", HttpStatus.OK.value());
							map.put("DATA", credit);
							return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		    			}else{
		    				map.put("MESSAGE", "FAILED");
							map.put("MSG", "The AR Credit Note with record id: "+tran.getTransId()+" does not exist.");
		    			}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The AR Credit Note with record id: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "AR Receipt":
					sql = "SELECT COUNT(*) as CRow FROM tblReceipt WHERE RcpID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						ARReceipt rcp = new ARReceipt();
						rcp.setRcpId(tran.getTransId());
						ARReceipt receipt = rcpService.getARReceipt(rcp, dataSource);
						if(receipt != null){
		    				map.put("MESSAGE", "SUCCESS");
							map.put("STATUS", HttpStatus.OK.value());
							map.put("DATA", receipt);
							return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		    			}else{
		    				map.put("MESSAGE", "FAILED");
							map.put("MSG", "The AR Receipt with record id: "+tran.getTransId()+" does not exist.");
		    			}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The AR Receipt with record id: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "IC Transfer":
					sql = "SELECT COUNT(*) as CRow FROM tblTransfer WHERE TrfID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						ICTransfer trf = new ICTransfer();
						trf.setTrfId(tran.getTransId());
						ICTransfer transfer = trfService.getICTransfer(trf, dataSource);
						if(transfer != null){
		    				map.put("MESSAGE", "SUCCESS");
							map.put("STATUS", HttpStatus.OK.value());
							map.put("DATA", transfer);
							return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		    			}else{
		    				map.put("MESSAGE", "FAILED");
							map.put("MSG", "The IC Transfer with record id: "+tran.getTransId()+" does not exist.");
		    			}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The IC Transfer with record id: "+tran.getTransId()+" does not exist.");
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
						ICAdjustment adj = new ICAdjustment();
						adj.setAdjId(tran.getTransId());
						ICAdjustment adjustment = adjService.getICAdjustment(adj, dataSource);
						if(adjustment != null){
							map.put("MESSAGE", "SUCCESS");
							map.put("STATUS", HttpStatus.OK.value());
							map.put("DATA", adjustment);
							return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The IC Adjustment with record id: "+tran.getTransId()+" does not exist.");
						}						
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The IC Adjustment with record id: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "Cash Transfer":
					sql = "SELECT COUNT(*) as CRow FROM tblMoney_Company_Transfer WHERE TrID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						CashTransfer ctf = new CashTransfer();
						ctf.setCtfId(tran.getTransId());
						CashTransfer cashTransfer = ctfService.getCashTransfer(ctf, dataSource);
						if(cashTransfer != null){
							map.put("MESSAGE", "SUCCESS");
							map.put("STATUS", HttpStatus.OK.value());
							map.put("DATA", cashTransfer);
							return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The Cash Transfer with record id: "+tran.getTransId()+" does not exist.");
						}
						
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The Cash Transfer with record id: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "Cash Advance":
					sql = "SELECT COUNT(*) as CRow FROM tblCashAdvance WHERE CaID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						CashAdvance ca  = new CashAdvance();
						ca.setCaId(tran.getTransId());
						CashAdvance cash = caService.getCashAdvance(ca, dataSource);
						if(cash!=null){
							map.put("MESSAGE", "SUCCESS");
							map.put("STATUS", HttpStatus.OK.value());
							map.put("DATA", cash);
							return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The Cash Advance with record id: "+tran.getTransId()+" does not exist.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The Cash Advance with record id: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "Cash Advance Clearance":
					sql = "SELECT COUNT(*) as CRow FROM tblClearance_History WHERE ClID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						CashAdvanceClearance cac = new CashAdvanceClearance();
						cac.setClId(tran.getTransId());
						CashAdvanceClearance cash = clService.getCashAdvanceClearance(cac, dataSource);
						if(cash!=null){
							map.put("MESSAGE", "SUCCESS");
							map.put("STATUS", HttpStatus.OK.value());
							map.put("DATA", cash);
							return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The Cash Advance Clearance with record id: "+tran.getTransId()+" does not exist.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The Cash Advance Clearance with record id: "+tran.getTransId()+" does not exist.");
					}
			    	break;
				case "GL Entries":
					sql = "SELECT COUNT(*) as CRow FROM tblJournal WHERE JID = '"+tran.getTransId()+"'";
					if(post.checkExist(sql, dataSource)){
						Journal j = new Journal();						
						j.setjId(Integer.parseInt(tran.getTransId()));
						Journal jn = jnService.getJournal(j, dataSource);
						if(jn!=null){
							map.put("MESSAGE", "SUCCESS");
							map.put("STATUS", HttpStatus.OK.value());
							map.put("DATA", jn);
							return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
						}else{
							map.put("MESSAGE", "FAILED");
							map.put("MSG", "The GL Entries with record id: "+tran.getTransId()+" does not exist.");
						}
					}else{
						map.put("MESSAGE", "FAILED");
						map.put("MSG", "The GL Entries with record id: "+tran.getTransId()+" does not exist.");
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
