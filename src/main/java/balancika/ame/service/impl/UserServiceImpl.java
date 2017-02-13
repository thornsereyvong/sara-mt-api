package balancika.ame.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.Connection;

import balancika.ame.entities.CrmUserLogin;
import balancika.ame.entities.MeDataSource;
import balancika.ame.service.UserService;
import balancika.ame.utilities.DBConnection;

@Repository
public class UserServiceImpl implements UserService{
	
	@Autowired
	private MeDataSource dataSource;
	
	@Transactional
	@Override
	public CrmUserLogin findUserByUsername(String username) {
		ResultSet rs = null;
		CrmUserLogin user = null;
		try (Connection con = DBConnection.getConnection(dataSource)){
			String sql = "SELECT COALESCE(u.UID, '') 'userId',COALESCE(u.UName,'') 'username', COALESCE(u.UType,'') 'type', COALESCE(u.UPassword, '') 'Password',COUNT(u.UID) as 'Has',COUNT(a.UID) as 'Permission' FROM tbluser u LEFT JOIN tbluserapp a on u.UID = a.UID and a.AppID = 'WEB' WHERE u.UID = ? LIMIT 1";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while(rs.next()){
				user = new CrmUserLogin();
				user.setUserID(rs.getString("userId"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setType(rs.getString("type"));
				user.setHas(rs.getInt("Has"));
				user.setPermission(rs.getInt("permission"));
				return user;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
