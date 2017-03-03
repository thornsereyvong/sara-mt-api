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

import balancika.ame.entities.Authorization;
import balancika.ame.entities.AuthorizationDetail;
import balancika.ame.entities.AuthorizationGroup;
import balancika.ame.entities.Employee;
import balancika.ame.entities.MeDataSource;
import balancika.ame.service.AuthorizationService;
import balancika.ame.utilities.DBConnection;

@Repository
public class AuthorizationServiceImpl implements AuthorizationService {
	
	@Transactional
	@Override
	public List<Authorization> listAuthorization(MeDataSource dataSource) throws SQLException {
		Authorization auth = null;
		CallableStatement cstmt = null;
		try (Connection con = DBConnection.getConnection(dataSource)){
			String para1 = "AME";
			String para2 = "ALL";
			String sql = "{call spHRMSearchAuthorisation(?,?)}";
			cstmt = (CallableStatement) con.prepareCall(sql);
			cstmt.setString(1, para1);	
			cstmt.setString(2, para2);	
			ResultSet rs = cstmt.executeQuery();
			ArrayList<Authorization> arrauth = new ArrayList<Authorization>();
			while(rs.next()){
				auth = new Authorization();
				
				auth.setAuthId(rs.getString("Auth_ID"));
				auth.setAuthName(rs.getString("Auth_Name"));
				auth.setAuthType(rs.getString("Auth_Type"));
				auth.setAuthAndOr(rs.getString("Auth_AndOr"));
				auth.setAuthAmount(rs.getString("Auth_Amount"));
				
				arrauth.add(auth);
			}
			rs.close();			
			return arrauth;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 cstmt.close();
		}
		return null;
	}

	@Override
	public Map<String, Object> createAuthorization(MeDataSource dataSource, Authorization authori) throws SQLException {
		
		
		try (Connection con = DBConnection.getConnection(dataSource)){
			
			CallableStatement cstmt = null;
			String AuthName = authori.getAuthName();
			String Auth= "";
			Auth += "(";
			Auth += "'TempAuth_ID',";
			Auth += "'" + authori.getAuthName() + "',";
			Auth += "'" + authori.getAuthType() + "',";
			Auth += "'" + authori.getAuthAndOr() + "',";
			if(authori.getAuthType().toLowerCase().equals("individual")){
				if(authori.getAuthAndOr().toLowerCase().equals("and")){
					Auth += "'" + authori.getAuthAmount() + "'";
				}else{
					Auth += "'" + 1 + "'";
				}	
			}else{
				Auth += "'" + 0 + "'";
			}
			Auth += ")";
				
			String AuthDetail = "";
			String AuthGroupID = "";
			String AuthGroupAmount="";
			
			if(authori.getAuthorizationDetail() != null && authori.getAuthorizationDetail().size()>0){
				for(AuthorizationDetail authorisationDetail : authori.getAuthorizationDetail()){
					
					AuthDetail += "(";
					AuthDetail += "'TempAuth_ID',";
					AuthDetail += "'" + authorisationDetail.getAuthEmpId() + "',";
					AuthDetail += "'" + authorisationDetail.getAuthGroupId() + "',";
					AuthDetail += "'" + authorisationDetail.getAuthGroupAndOr() + "',";
					if(authori.getAuthType().toLowerCase().equals("group")){
						if(authorisationDetail.getAuthGroupAndOr().toLowerCase().equals("and")){
							AuthDetail += "'" + authorisationDetail.getAuthGroupAmount() + "'";
						}else{
							AuthDetail += "'" + 1 + "'";
						}
					}else{
						AuthDetail += "'" + 0 + "'";
					}
					
					AuthDetail += "),";
					AuthGroupID += authorisationDetail.getAuthGroupId() + ",";
					AuthGroupAmount += "" + authorisationDetail.getAuthGroupAmount() + ",";
				}
				if (AuthDetail != "") {
					AuthDetail = AuthDetail.substring(0, AuthDetail.length() - 1);
				}
			}else{
				return null;
			}
			
			
			
			String sql = "{call spHRMAddAuthorisation(?,?,?,?,?,?,?,?,?)}";
			cstmt = (CallableStatement) con.prepareCall(sql);
			cstmt.setString(1, dataSource.getUserid());	
			cstmt.setString(2, AuthName);
			cstmt.setString(3, Auth);	
			cstmt.setString(4, AuthDetail);
			cstmt.setString(5, authori.getAuthType());	
			cstmt.setString(6, authori.getAuthAmount());
			cstmt.setLong(7,  authori.getAuthorizationDetail().size());	
			cstmt.setString(8, AuthGroupID);
			cstmt.setString(9, AuthGroupAmount);	
			boolean results = cstmt.execute();
			Map<String, Object> m = new HashMap<String, Object>();
			while(results){
				ResultSet rs = cstmt.getResultSet();
				
				 while (rs.next()) {
					 m.put("MESSAGE", rs.getString("ID"));
					 System.out.println(rs.getString("ID"));
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
		}
	
		return null;
	}

	@Override
	public Map<String, Object> deleteAuthorization(MeDataSource dataSource, String authId) throws SQLException {
		CallableStatement cstmt = null;
		Map<String, Object> m = new HashMap<String, Object>();
		try (Connection con = DBConnection.getConnection(dataSource)){
			
			String sql = "{call spHRMDeleteAuthorisation(?,?)}";
			cstmt = (CallableStatement) con.prepareCall(sql);
			cstmt.setString(1, dataSource.getUserid());	
			cstmt.setString(2, authId);
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
	public Map<String, Object> getByIdAuthorization(MeDataSource dataSource, String authId) throws SQLException {
		CallableStatement cstmt = null;
		Map<String, Object> m = new HashMap<String, Object>();
		try (Connection con = DBConnection.getConnection(dataSource)){
			
			String sql = "{call spHRMGetAuthorisationID(?)}";
			cstmt = (CallableStatement) con.prepareCall(sql);
			cstmt.setString(1, authId);
			boolean results = cstmt.execute();
			while(results){
				
				ResultSet rs1 = cstmt.getResultSet();
				
				List<Authorization> TempArrAuthori = new ArrayList<Authorization>();
				m.put("MESSAGE", "SUCCESS");
				while (rs1.next()) {
					Authorization arryAuth= new Authorization();
					
					arryAuth.setAuthId(rs1.getString("Auth_ID"));
					arryAuth.setAuthName(rs1.getString("Auth_Name"));
					arryAuth.setAuthType(rs1.getString("Auth_Type"));
					arryAuth.setAuthAndOr(rs1.getString("Auth_AndOr"));
					arryAuth.setAuthAmount(rs1.getString("Auth_Amount"));
					TempArrAuthori.add(arryAuth);
					m.put("authorization", TempArrAuthori);
		        }
		        rs1.close();
		        
		        results = cstmt.getMoreResults();
		        ResultSet rs2 = cstmt.getResultSet();
		        
		        List<AuthorizationDetail> TempArrAuthDetail = new ArrayList<AuthorizationDetail>();
		        
		        while (rs2.next()) {
		        	AuthorizationDetail arrAuthDetail = new AuthorizationDetail();
		        	arrAuthDetail.setAuthId(rs2.getString("Auth_ID"));
		        	arrAuthDetail.setAuthEmpId(rs2.getString("Auth_EmpID"));
		        	arrAuthDetail.setAuthGroupId(rs2.getString("Auth_GroupID"));
		        	arrAuthDetail.setAuthGroupAndOr(rs2.getString("Auth_GroupAndOr"));
		        	arrAuthDetail.setAuthGroupAmount(rs2.getString("Auth_GroupAmount"));
		        	TempArrAuthDetail.add(arrAuthDetail);
					m.put("authorizationDetail", TempArrAuthDetail);
		        }	 
		        rs2.close();
		        
		        
		         
		        results = cstmt.getMoreResults();
		        ResultSet rs3 = cstmt.getResultSet();
		        List<Employee> tempArrauth = new ArrayList<Employee>();
		       
		        while (rs3.next()) {
		        	Employee empDetail = new Employee();
		        	
		        	boolean dataCheck = true;
		        	
		        	if(rs3.getString("EmpID").equals(rs3.getString("Auth_EmpID")))
		        		dataCheck = true;
		        	else
		        		dataCheck = false;

		        	empDetail.setEmpID(rs3.getString("EmpID"));
		        	empDetail.setEmpName(rs3.getString("EmpName"));
		        	empDetail.setStatusCheck(dataCheck);
		        	tempArrauth.add(empDetail);
		        	m.put("Employees", tempArrauth);
		        }	 
		        rs3.close();
		        
		        results = cstmt.getMoreResults();
		        ResultSet rs4 = cstmt.getResultSet();
		        List<AuthorizationGroup> TempArrAuthGro = new ArrayList<AuthorizationGroup>();
		        int itemNumber =0;
		        
				while (rs4.next()) {
					itemNumber++;
					AuthorizationGroup arryAuthGroup = new AuthorizationGroup();
					
					boolean dataCheckGr = true;
		        	
		        	if(rs4.getString("AuthGroup_ID").equals(rs4.getString("Auth_GroupID")))
		        		dataCheckGr = true;
		        	else
		        		dataCheckGr = false;
		        	
		        	String checkAndOrAmount = "";
		        	if(rs4.getString("Auth_GroupAndOr").equals("Or")){
		        		checkAndOrAmount = "";		        	
		        	}else{
		        		checkAndOrAmount = rs4.getString("Auth_GroupAmount");
		        	}
		        	
					arryAuthGroup.setAuthGroupId(rs4.getString("AuthGroup_ID"));
					arryAuthGroup.setAuthGroupName(rs4.getString("AuthGroup_Name"));
					arryAuthGroup.setAuthGroupDesc(rs4.getString("AuthGroup_Description"));
					arryAuthGroup.setAuthGroupCount(rs4.getString("Count"));
					arryAuthGroup.setAndOrCheck(rs4.getString("Auth_GroupAndOr"));
					arryAuthGroup.setAmountCheck(checkAndOrAmount);
					arryAuthGroup.setStatusCheck(dataCheckGr);
					arryAuthGroup.setItemNumber(itemNumber);
					TempArrAuthGro.add(arryAuthGroup);
					m.put("authorizationGroup", TempArrAuthGro);
		        }
		        rs4.close();
		         
		        return m;
			}
			
			
		} catch (Exception e) {
			m.put("MESSAGE", "FAIL");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Map<String, Object> updateAuthorization(MeDataSource dataSource, Authorization authori) throws SQLException {
		try (Connection con = DBConnection.getConnection(dataSource)){
			
			CallableStatement cstmt = null;
			
			String AuthID = authori.getAuthId();
			String AuthName = authori.getAuthName();
			String Auth = "";
			Auth += "Auth_ID='" + authori.getAuthId() + "',";
			Auth += "Auth_Name='" + authori.getAuthName() + "',";
			Auth += "Auth_Type='" + authori.getAuthType() + "',";
			Auth += "Auth_AndOr='" + authori.getAuthAndOr() + "',";
			if(authori.getAuthAndOr().toLowerCase().equals("and")){
				Auth += "Auth_Amount='" + authori.getAuthAmount() + "'";
			}else{
				Auth += "Auth_Amount='" + 1 + "'";
			}	
			
			String AuthDetail = "";
			String AuthGroupID = "";
			String AuthGroupAmount="";
			if(authori.getAuthorizationDetail() != null){
				for(AuthorizationDetail authorisationDetail : authori.getAuthorizationDetail()){
					AuthDetail += "(";
					AuthDetail += "'TempAuth_ID',";
					AuthDetail += "'" + authorisationDetail.getAuthEmpId() + "',";
					AuthDetail += "'" + authorisationDetail.getAuthGroupId() + "',";
					AuthDetail += "'" + authorisationDetail.getAuthGroupAndOr() + "',";
					if(authorisationDetail.getAuthGroupAndOr().toLowerCase().equals("and")){
						AuthDetail += "'" + authorisationDetail.getAuthGroupAmount() + "'";
					}else{
						AuthDetail += "'" + 1 + "'";
					}
					AuthDetail += "),";
					AuthGroupID += authorisationDetail.getAuthGroupId() + ",";
					AuthGroupAmount += "" + authorisationDetail.getAuthGroupAmount() + ",";
				}
				if (AuthDetail != "") {
					AuthDetail = AuthDetail.substring(0, AuthDetail.length() - 1);
				}
			}
			
			
			
			String sql = "{call spHRMUpdateAuthorisation(?,?,?,?,?,?,?,?,?,?)}";
			cstmt = (CallableStatement) con.prepareCall(sql);
			cstmt.setString(1, dataSource.getUserid());	
			cstmt.setString(2, AuthID);
			cstmt.setString(3, AuthName);
			cstmt.setString(4, Auth);	
			cstmt.setString(5, AuthDetail);
			cstmt.setString(6, authori.getAuthType());	
			cstmt.setString(7, authori.getAuthAmount());
			cstmt.setLong(8,  authori.getAuthorizationDetail().size());	
			cstmt.setString(9, AuthGroupID);
			cstmt.setString(10, AuthGroupAmount);	
			boolean results = cstmt.execute();
			Map<String, Object> m = new HashMap<String, Object>();
			while(results){
				ResultSet rs = cstmt.getResultSet();
				
				 while (rs.next()) {
					 m.put("MESSAGE", rs.getString("Exist"));
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
		}
	
		return null;
	}

}
