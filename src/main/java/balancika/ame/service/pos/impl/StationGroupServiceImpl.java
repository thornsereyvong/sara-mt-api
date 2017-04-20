package balancika.ame.service.pos.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

import balancika.ame.entities.MeDataSource;
import balancika.ame.service.pos.StationGroupService;
import balancika.ame.utilities.DBConnection;
import balancika.ame.utilities.SQLUtil;

@Repository
public class StationGroupServiceImpl implements StationGroupService{

	@Override
	public Map<String, Object> listStationGroup(MeDataSource dataSource) {
		try (Connection con = DBConnection.getConnection(dataSource)){			
			String sql = "call ame_res_list_all_station_group()";
			String[] str = {"stationGroup","station"};
			CallableStatement c = con.prepareCall(sql);
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

	@Override
	public List<Map<String, Object>> listStationByStationGroup(String stationId, MeDataSource dataSource) {
		try (Connection con = DBConnection.getConnection(dataSource)){			
			String sql = "call ame_res_list_all_station_by_SG(?)";
			CallableStatement c = con.prepareCall(sql);
			c.setString(1, stationId);
			ResultSet rs = c.executeQuery();
			return SQLUtil.aliasRSToMap(rs);			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
