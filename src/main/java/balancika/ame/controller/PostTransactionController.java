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
	
}
