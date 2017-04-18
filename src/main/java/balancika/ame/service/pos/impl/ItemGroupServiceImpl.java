package balancika.ame.service.pos.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

import balancika.ame.entities.MeDataSource;
import balancika.ame.service.pos.ItemGroupService;
import balancika.ame.utilities.DBConnection;
import balancika.ame.utilities.SQLUtil;

@Repository
public class ItemGroupServiceImpl implements ItemGroupService{

	@Override
	public Map<String, Object> listItemGroupGroup(String filter,MeDataSource dataSource) {
		try (Connection con = DBConnection.getConnection(dataSource)){			
			String sql = "call ame_res_startup_order_form(?, ?)";
			String[] str = {"itemGroup","item"};
			CallableStatement c = con.prepareCall(sql);
			c.setString(1, "i.ItemID");
			c.setString(2, filter);
			boolean r = c.execute();
			int i=0;
			Map<String, Object> map = null;
			while(r){
				if(i==0){
					map = new HashMap<String, Object>();
				}				
				ResultSet rs = c.getResultSet();
				map.put(str[i], SQLUtil.aliasRSToMap(rs));
				rs.close();
				r = c.getMoreResults();
				i++;
			}
			return map;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
