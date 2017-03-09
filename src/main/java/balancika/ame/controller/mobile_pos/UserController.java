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

import balancika.ame.entities.CrmUserLogin;
import balancika.ame.service.UserService;
import balancika.ame.utilities.PasswordEncrypt;

@RestController
@RequestMapping("/api/pos/user/")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = {"/login"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> mobileLogin(@RequestBody CrmUserLogin user){
		Map<String, Object> map = new HashMap<String, Object>();
		CrmUserLogin u = userService.mobileLoginPOS(user);
		
		if(u != null){
			if((!u.getUserID().equalsIgnoreCase(user.getUserID())) || u.getHas() == 0 || !user.getPassword().equals(new PasswordEncrypt().BalDecrypt(u.getPassword()))){				
				map.put("MESSAGE", "FAILED");
				map.put("MSG", "Invalid Username and password!");
			}else if(u.getPermission()==0){
				map.put("MESSAGE", "FAILED");
				map.put("MSG", "You have no permission! Please contact your administrator!");
			}else{
				map.put("MESSAGE", "SUCCESS");
				map.put("MSG", "Login Successfully!");
			}
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("MSG", "Invalid Username and password!");
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
}
