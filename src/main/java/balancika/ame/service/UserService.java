package balancika.ame.service;

import balancika.ame.entities.CrmUserLogin;

public interface UserService {
	CrmUserLogin findUserByUsername(String username);
	CrmUserLogin mobileLoginPOS(CrmUserLogin user);
}
