package balancika.ame.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.Connection;
import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.SDCompany;
import balancika.ame.service.DatabaseService;
import balancika.ame.utilities.DBConnection;

@Service
@Transactional
public class DatabaseServiceImpl implements DatabaseService{
	
	@Override
	public List<SDCompany> listAllSystemDatabase(MeDataSource dataSource) {
		SDCompany com = null;
		try (Connection con = DBConnection.getConnection(dataSource)){
			String sql = "SELECT ComID, ComName,DBName FROM tblcompany ;";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ArrayList<SDCompany> arrCom = new ArrayList<SDCompany>();
			while(rs.next()){
				com = new SDCompany();
				com.setComId(rs.getString("ComID"));
				com.setComName(rs.getString("ComName"));
				com.setDbName(rs.getString("DBName"));
				arrCom.add(com);
			}
			rs.close();
			return arrCom;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
