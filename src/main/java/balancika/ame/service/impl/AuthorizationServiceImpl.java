package balancika.ame.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;

import balancika.ame.entities.Authorization;
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

}
