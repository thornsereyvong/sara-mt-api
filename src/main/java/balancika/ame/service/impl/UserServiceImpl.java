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
import balancika.ame.utilities.FormPermission;
import balancika.ame.utilities.PasswordEncrypt;

@Repository
public class UserServiceImpl implements UserService{
	
	@Autowired
	private MeDataSource dataSource;
	
	@Transactional
	@Override
	public CrmUserLogin findUserByUsername(String username) {
		
		try (Connection con = DBConnection.getConnection(dataSource)){
			String sql = "SELECT COALESCE(u.UID, '') 'userId',COALESCE(u.UName,'') 'username', COALESCE(u.UType,'') 'type', COALESCE(u.UPassword, '') 'Password',COUNT(u.UID) as 'Has',COUNT(a.UID) as 'Permission' FROM tbluser u LEFT JOIN tbluserapp a on u.UID = a.UID and a.AppID = 'WEB' WHERE u.UID = ? LIMIT 1";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);			
			CrmUserLogin user = null;
			ResultSet rs = ps.executeQuery();
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

	@Override
	public CrmUserLogin mobileLoginPOS(CrmUserLogin user) {
		try (Connection con = DBConnection.getConnection(user.getDataSource())){
			String sql = "SELECT COALESCE(u.UID, '') 'userId',COALESCE(u.UName,'') 'username', COALESCE(u.UType,'') 'type', COALESCE(u.UPassword, '') 'Password',COUNT(u.UID) as 'Has',COUNT(a.UID) as 'Permission' FROM tbluser u LEFT JOIN tbluserapp a on u.UID = a.UID and a.AppID in ('POS','POS ADMIN') WHERE u.UID = ? LIMIT 1";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUserID());		
			CrmUserLogin u = null;
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				u = new CrmUserLogin();
				u.setUserID(rs.getString("userId"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setType(rs.getString("type"));
				u.setHas(rs.getInt("Has"));
				u.setPermission(rs.getInt("permission"));
				return u;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public FormPermission userFrmPermission(String userId, String frm, MeDataSource dataSource) {
		
		try (Connection con = DBConnection.getConnection(dataSource)){
			String sql = "SELECT * FROM tbluserpermission WHERE UID = ? AND FrmSysName = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, frm);
			ResultSet rs = ps.executeQuery();
			String str = "";
			FormPermission form = null;
			while(rs.next()){
				form = new FormPermission();
				form.setUserId(rs.getString("UID"));
				form.setForm(rs.getString("FrmSysName"));
				PasswordEncrypt p = new PasswordEncrypt();
				str = (userId+""+frm+"pread1").toUpperCase();
				if(str.equals(p.BalDecrypt(rs.getString("pRead")))){
					form.setPread(true);
				}else{
					form.setPread(false);
				}
				
				str = (userId+""+frm+"padd1").toUpperCase();
				if(str.equals(p.BalDecrypt(rs.getString("pAdd")))){
					form.setPadd(true);
				}else{
					form.setPadd(false);
				}
				
				str = (userId+""+frm+"pedit1").toUpperCase();
				if(str.equals(p.BalDecrypt(rs.getString("pEdit")))){
					form.setPedit(true);
				}else{
					form.setPedit(false);
				}
				
				str = (userId+""+frm+"pdelete1").toUpperCase();
				if(str.equals(p.BalDecrypt(rs.getString("pDelete")))){
					form.setPdelete(true);
				}else{
					form.setPdelete(false);
				}
				
				str = (userId+""+frm+"ppost1").toUpperCase();
				if(str.equals(p.BalDecrypt(rs.getString("pPost")))){
					form.setPpost(true);
				}else{
					form.setPpost(false);
				}
				
				str = (userId+""+frm+"pvoid1").toUpperCase();
				if(str.equals(p.BalDecrypt(rs.getString("pVoid")))){
					form.setPvoid(true);
				}else{
					form.setPvoid(true);
				}
			}
			return form;			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
