package balancika.ame.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;

import balancika.ame.entities.AuthorizationGroup;
import balancika.ame.entities.AuthorizationGroupDetail;
import balancika.ame.entities.Employee;
import balancika.ame.entities.MeDataSource;
import balancika.ame.service.AuthorizationGroupService;
import balancika.ame.utilities.DBConnection;


@Repository
public class AuthorizationGroupServiceImpl implements AuthorizationGroupService {

	@Transactional
	@Override
	public List<AuthorizationGroup> listAuthorizationGroup(MeDataSource dataSource) throws SQLException {
		AuthorizationGroup authGroup = null;
		CallableStatement cstmt = null;
		try (Connection con = DBConnection.getConnection(dataSource)){
			String para1 = "AME";
			String para2 = "all";
			String sql = "{call spHRMSearchAuthorisationGroup(?,?)}";
			cstmt = (CallableStatement) con.prepareCall(sql);
			cstmt.setString(1, para1);	
			cstmt.setString(2, para2);	
			ResultSet rs = cstmt.executeQuery();
			ArrayList<AuthorizationGroup> arrauthGro = new ArrayList<AuthorizationGroup>();
			int i = 1;
			while(rs.next()){
				authGroup = new AuthorizationGroup();
				
				authGroup.setAuthGroupId(rs.getString("AuthGroup_ID"));
				authGroup.setAuthGroupName(rs.getString("AuthGroup_Name"));
				authGroup.setAuthGroupDesc(rs.getString("AuthGroup_Description"));
				authGroup.setAuthGroupCount(rs.getString("Count"));	
				authGroup.setItemNumber(i);
				arrauthGro.add(authGroup);
				
				i++;
			}
			rs.close();			
			return arrauthGro;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 cstmt.close();
		}
		return null;
	}


	@Override
	public Map<String, Object> createAuthorizationGroup(AuthorizationGroup authoriGroup , MeDataSource dataSoruce)	throws SQLException {
		
		CallableStatement cbs = null;
		
		
		try (Connection con = DBConnection.getConnection(dataSoruce)){
			
			String AuthGroup = "";
					AuthGroup += "(";
					AuthGroup += "'TempAuth_ID',";
					AuthGroup += "'" + authoriGroup.getAuthGroupName() + "',";
					AuthGroup += "'" + authoriGroup.getAuthGroupDesc() + "'";
					AuthGroup += ")";
			
			
			
			String AuthGroupDetail = "";
			if(authoriGroup.getAuthGroupDetail() != null){
				for(AuthorizationGroupDetail authorisationGroupDetail : authoriGroup.getAuthGroupDetail()){
					AuthGroupDetail += "(";
					AuthGroupDetail += "'TempAuth_ID',";
					AuthGroupDetail += "'" + authorisationGroupDetail.getAuthGroupEmpId() + "'";
					AuthGroupDetail += "),";
				}
				if (AuthGroupDetail != "") {
					AuthGroupDetail = AuthGroupDetail.substring(0, AuthGroupDetail.length() - 1);
				}
			}
			
			Map<String, Object> m = new HashMap<String, Object>();
			
			String sql = "{call spHRMAddAuthorisationGroup(?,?,?,?)}";
			cbs = (CallableStatement) con.prepareCall(sql);
			cbs.setString(1, dataSoruce.getUserid());	
			cbs.setString(2, authoriGroup.getAuthGroupName());
			cbs.setString(3, AuthGroup);	
			cbs.setString(4, AuthGroupDetail);
			boolean results = cbs.execute();
			
			while(results){
				ResultSet rs = cbs.getResultSet();
				
				 while (rs.next()) {
					 m.put("MESSAGE", rs.getString("ID"));
		         }	 
		         rs.close();
		        
		        results = cbs.getMoreResults();
		        ResultSet rs2 = cbs.getResultSet();
		        
		        while (rs2.next()) {
		        	 m.put("DESCRIPTION", rs2.getString("alert"));
		         }	 
		         rs.close();
		         
		         return m;
			}
	
			
			 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cbs.close();
		}
		return null;
	}


	@Override
	public Map<String, Object> deleteAuthorizationGroup(MeDataSource dataSource,String ID)throws SQLException {
		CallableStatement cstmt = null;
		Map<String, Object> m = new HashMap<String, Object>();
		try (Connection con = DBConnection.getConnection(dataSource)){
			
			String sql = "{call spHRMDeleteAuthorisationGroup(?,?)}";
			cstmt = (CallableStatement) con.prepareCall(sql);
			cstmt.setString(1, dataSource.getUserid());	
			cstmt.setString(2, ID);
			if(cstmt.executeUpdate() > 0){
				m.put("MESSAGE", "SUCCESS");
			}else{
				m.put("MESSAGE", "FAIL");
			}
			
			return m;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 cstmt.close();
		}
		return null;
	}


	@Override
	public Map<String, Object> getAuthorizationGroup(String ID, MeDataSource dataSource) throws SQLException {
		CallableStatement cstmt = null;
		Map<String, Object> m = new HashMap<String, Object>();
		try (Connection con = DBConnection.getConnection(dataSource)){
			
			String sql = "{call spHRMGetAuthorisationGroupID(?)}";
			cstmt = (CallableStatement) con.prepareCall(sql);
			cstmt.setString(1, ID);
			
			boolean results = cstmt.execute();
			while(results){
				ResultSet rs = cstmt.getResultSet();
				
				List<AuthorizationGroup> TempArrauthGro = new ArrayList<AuthorizationGroup>();
				m.put("MESSAGE", "SUCCESS");
				while (rs.next()) {
					AuthorizationGroup arryAuthGroup = new AuthorizationGroup();
					
					arryAuthGroup.setAuthGroupId(rs.getString("AuthGroup_ID"));
					arryAuthGroup.setAuthGroupName(rs.getString("AuthGroup_Name"));
					arryAuthGroup.setAuthGroupDesc(rs.getString("AuthGroup_Description"));
					TempArrauthGro.add(arryAuthGroup);
					m.put("authorizationGroup", TempArrauthGro);
		        }
		        rs.close();
		        
		        results = cstmt.getMoreResults();
		        ResultSet rs2 = cstmt.getResultSet();
		        
		        List<AuthorizationGroupDetail> TempArrauthGroDetail = new ArrayList<AuthorizationGroupDetail>();
		        
		        while (rs2.next()) {
		        	AuthorizationGroupDetail arrauthGroDetail = new AuthorizationGroupDetail();
		        	arrauthGroDetail.setAuthGroupId(rs2.getString("AuthGroup_ID"));
		        	arrauthGroDetail.setAuthGroupEmpId(rs2.getString("AuthGroup_EmpID"));
		        	TempArrauthGroDetail.add(arrauthGroDetail);
					m.put("authorizationGroupDetail", TempArrauthGroDetail);
		        }	 
		        rs.close();
		        
		        
		         
		        results = cstmt.getMoreResults();
		        ResultSet rs3 = cstmt.getResultSet();
		        List<Employee> tempArrauth = new ArrayList<Employee>();
		       
		        while (rs3.next()) {
		        	Employee empDetail = new Employee();
		        	
		        	boolean dataCheck = true;
		        	
		        	if(rs3.getString("EmpID").equals(rs3.getString("AuthGroup_EmpID")))
		        		dataCheck = true;
		        	else
		        		dataCheck = false;
		        	
		        	//System.out.println(rs3.getString("EmpID")+" "+rs3.getString("AuthGroup_EmpID"));
		        	//System.out.println(dataCheck);
		        	empDetail.setEmpID(rs3.getString("EmpID"));
		        	empDetail.setEmpName(rs3.getString("EmpName"));
		        	empDetail.setStatusCheck(dataCheck);
		        	tempArrauth.add(empDetail);
		        	m.put("Employees", tempArrauth);
		        }	 
		        rs.close();
		         
		        return m;
			}
			
			
		} catch (Exception e) {
			m.put("MESSAGE", "FAIL");
			e.printStackTrace();
		}
		
		return null;
		
	}


	@Override
	public Map<String, Object> updateAuthorizationGroup(AuthorizationGroup authoriGroup, MeDataSource dataSource)throws SQLException {
		
		CallableStatement cbs = null;
		
		
		try (Connection con = DBConnection.getConnection(dataSource)){
			
			String AuthGroupID = authoriGroup.getAuthGroupId();
			String AuthGroupName = authoriGroup.getAuthGroupName();
			
			String AuthGroup = "";
			AuthGroup += "AuthGroup_ID='" + authoriGroup.getAuthGroupId() + "',";
			AuthGroup += "AuthGroup_Name='" + authoriGroup.getAuthGroupName() + "',";
			AuthGroup += "AuthGroup_Description='" + authoriGroup.getAuthGroupDesc() + "'";
			
			String AuthGroupDetail = "";
			if(authoriGroup.getAuthGroupDetail() != null){
				for(AuthorizationGroupDetail authorisationGroupDetail : authoriGroup.getAuthGroupDetail()){
					AuthGroupDetail += "(";
					AuthGroupDetail += "'"+  authoriGroup.getAuthGroupId() +"',";
					AuthGroupDetail += "'" + authorisationGroupDetail.getAuthGroupEmpId() + "'";
					AuthGroupDetail += "),";
				}
				if (AuthGroupDetail != "") {
					AuthGroupDetail = AuthGroupDetail.substring(0, AuthGroupDetail.length() - 1);
				}
			}
			
			Map<String, Object> m = new HashMap<String, Object>();
			
			String sql = "{call spHRMUpdateAuthorisationGroup(?,?,?,?,?)}";
			cbs = (CallableStatement) con.prepareCall(sql);
			cbs.setString(1, dataSource.getUserid());	
			cbs.setString(2, AuthGroupID);
			cbs.setString(3, AuthGroupName);	
			cbs.setString(4, AuthGroup);
			cbs.setString(5, AuthGroupDetail);
			
			boolean results = cbs.execute();
			
			while(results){
				ResultSet rs = cbs.getResultSet();
				
				 while (rs.next()) {
					 m.put("MESSAGE", rs.getString("Exist"));
		         }	 
		         rs.close();
		        
		        results = cbs.getMoreResults();
		        ResultSet rs2 = cbs.getResultSet();
		        
		        while (rs2.next()) {
		        	 m.put("DESCRIPTION", rs2.getString("alert"));
		         }	 
		         rs.close();
		         
		         return m;
			}
	
			
			 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cbs.close();
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
}
