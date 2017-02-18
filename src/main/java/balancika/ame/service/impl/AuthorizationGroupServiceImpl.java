package balancika.ame.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;

import balancika.ame.entities.AuthorizationGroup;
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
			while(rs.next()){
				authGroup = new AuthorizationGroup();
				
				authGroup.setAuthorGroupId(rs.getString("AuthGroup_ID"));
				authGroup.setAuthorGroupName(rs.getString("AuthGroup_Name"));
				authGroup.setAuthorGroupDesc(rs.getString("AuthGroup_Description"));
				
				arrauthGro.add(authGroup);
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
	
}
