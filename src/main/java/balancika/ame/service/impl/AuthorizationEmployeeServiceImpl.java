package balancika.ame.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;

import balancika.ame.entities.AuthorizationEmployee;
import balancika.ame.entities.MeDataSource;
import balancika.ame.service.AuthorizationEmployeeService;
import balancika.ame.utilities.DBConnection;

@Service
public class AuthorizationEmployeeServiceImpl implements AuthorizationEmployeeService {

	@Transactional
	@Override
	public List<AuthorizationEmployee> listByEmpIdAuthorizationEmployee(MeDataSource dataSource, String empId)throws SQLException {
		AuthorizationEmployee auth = null;
		CallableStatement cstmt = null;
		try (Connection con = DBConnection.getConnection(dataSource)){
			String sql = "{call spHRMGetEmployeeIDAuthorisation(?)}";
			cstmt = (CallableStatement) con.prepareCall(sql);
			cstmt.setString(1, empId);	
			ResultSet rs = cstmt.executeQuery();
			ArrayList<AuthorizationEmployee> arrauth = new ArrayList<AuthorizationEmployee>();
			while(rs.next()){
				auth = new AuthorizationEmployee();
				
				auth.setAuthId(rs.getString("Auth_ID"));
				auth.setAuthProcess(rs.getString("Auth_Process"));
				auth.setEmpId(rs.getString("Emp_ID"));
				auth.setAuthName(rs.getString("Auth_Name"));
				
				
				arrauth.add(auth);
			}
			rs.close();			
			return arrauth;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Map<String, Object> deleteByIdAuthorizationEmployee(MeDataSource dataSource, String empId, String process,String authId) throws SQLException {
		CallableStatement cstmt = null;
		Map<String, Object> m = new HashMap<String, Object>();
		try (Connection con = DBConnection.getConnection(dataSource)){
			
			String sql = "{call spHRMDeleteEmployeeAuthorisationID(?,?,?,?)}";
			cstmt = (CallableStatement) con.prepareCall(sql);
			cstmt.setString(1, dataSource.getUserid());	
			cstmt.setString(2, empId);
			cstmt.setString(3, process);
			cstmt.setString(4, authId);
			boolean results = cstmt.execute();
			
			while(results){
				ResultSet rs = cstmt.getResultSet();
				
				 while (rs.next()) {
					 m.put("MESSAGE", rs.getString("Message"));
		         }	 
		         rs.close();
		        
		        results = cstmt.getMoreResults();
		        ResultSet rs2 = cstmt.getResultSet();
		        
		        while (rs2.next()) {
		        	 m.put("DESCRIPTION", rs2.getString("alert"));
		         }	 
		         rs.close();
		         
		         return m;
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 cstmt.close();
		}
		return null;
	}

	
}
