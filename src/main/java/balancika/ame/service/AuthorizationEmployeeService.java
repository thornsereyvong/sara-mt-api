package balancika.ame.service;

import java.sql.SQLException;
import java.util.List;


import java.util.Map;

import balancika.ame.entities.AuthorizationEmployee;
import balancika.ame.entities.MeDataSource;


public interface AuthorizationEmployeeService {
	List<AuthorizationEmployee>  listByEmpIdAuthorizationEmployee(MeDataSource dataSource, String empId) throws SQLException;
	Map<String, Object> deleteByIdAuthorizationEmployee(MeDataSource dataSource, String empId, String process, String authId) throws SQLException;
	Map<String, Object> createAuthorizationEmployee(MeDataSource dataSource,AuthorizationEmployee authEmp) throws SQLException;
}
