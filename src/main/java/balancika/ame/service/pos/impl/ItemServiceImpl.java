package balancika.ame.service.pos.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

import balancika.ame.entities.MeDataSource;
import balancika.ame.service.pos.ItemService;
import balancika.ame.utilities.DBConnection;
import balancika.ame.utilities.SQLUtil;

@Repository
public class ItemServiceImpl implements ItemService{

	@Override
	public List<Map<String, Object>> listItemDetail(String filter, MeDataSource dataSource) {
		try (Connection con = DBConnection.getConnection(dataSource)){			
			String[] filters = filter.split("::");
			String sql = "call ame_res_list_item_detail(?,?,?,?)";
			CallableStatement c = con.prepareCall(sql);
			c.setString(1, filters[0]);
			c.setString(2, filters[1]);
			c.setString(3, filters[2]);
			c.setString(4, filters[3]);
			ResultSet rs = c.executeQuery();
			return SQLUtil.aliasRSToMap(rs);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
