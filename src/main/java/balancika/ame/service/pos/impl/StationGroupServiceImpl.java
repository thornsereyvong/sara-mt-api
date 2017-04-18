package balancika.ame.service.pos.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.setting.PriceCode;
import balancika.ame.entities.setting.Station;
import balancika.ame.entities.setting.StationGroup;
import balancika.ame.service.pos.StationGroupService;
import balancika.ame.utilities.DBConnection;

public class StationGroupServiceImpl implements StationGroupService{

	@Override
	public List<StationGroup> listStationGroup(MeDataSource dataSource) {
		// TODO Auto-generated method stub
		try (Connection con = DBConnection.getConnection(dataSource)){
			
			String sql = "call ame_res_list_all_station_group()";
			CallableStatement c = con.prepareCall(sql);
			ResultSet rs  = c.executeQuery();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
