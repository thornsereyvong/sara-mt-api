package balancika.ame.service.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.Connection;
import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.SDCompany;
import balancika.ame.service.DatabaseService;
import balancika.ame.utilities.DBConnection;
import balancika.ame.utilities.SQLUtil;

@Service
@Transactional
public class DatabaseServiceImpl implements DatabaseService{
	
	@Override
	public List<SDCompany> listAllSystemDatabase(MeDataSource dataSource){		
		try (Connection con = DBConnection.getConnection(dataSource)){
			String sql = "SELECT ComID, ComName,DBName FROM tblcompany ;";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			SDCompany com = null;
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

	@Override
	public Map<String, Object> getDefaultConfig(MeDataSource dataSource) {
		try (Connection con = DBConnection.getConnection(dataSource)){
			String sql = "{call ame_pos_conf_default()}";
			CallableStatement c = con.prepareCall(sql);
			boolean r = c.execute();
			int i=0;
			Map<String, Object> map = new HashMap<String, Object>();
			String[] str = {"language","customer","location"};
			while(r){
				 ResultSet rs = c.getResultSet();
				 map.put(str[i], SQLUtil.aliasRSToMap(rs));
				 rs.close();
				 r = c.getMoreResults();
				 i++;
			}			
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> showAllDatabase(MeDataSource dataSource) {
		try (Connection con = DBConnection.getConnection(dataSource)){
			String sql = "show databases where `Database` NOT IN ('information_schema', 'test', 'mysql', 'performance_schema','systemdatabase')";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();					
			return SQLUtil.aliasRSToMap(rs);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
