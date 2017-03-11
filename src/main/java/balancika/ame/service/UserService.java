package balancika.ame.service;

import balancika.ame.entities.CrmUserLogin;
import balancika.ame.entities.MeDataSource;
import balancika.ame.utilities.FormPermission;

public interface UserService {
	CrmUserLogin findUserByUsername(String username);
	CrmUserLogin mobileLoginPOS(CrmUserLogin user);
	FormPermission userFrmPermission(String userId, String frm, MeDataSource dataSource);
}
