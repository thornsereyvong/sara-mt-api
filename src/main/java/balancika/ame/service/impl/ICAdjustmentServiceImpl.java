package balancika.ame.service.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.maintain.Item;
import balancika.ame.entities.setting.Class;
import balancika.ame.entities.setting.Location;
import balancika.ame.entities.setting.Uom;
import balancika.ame.entities.tansaction.ICAdjustment;
import balancika.ame.entities.tansaction.ICAdjustmentDetail;
import balancika.ame.service.ICAdjustmentService;
import balancika.ame.utilities.DBConnection;

public class ICAdjustmentServiceImpl implements ICAdjustmentService{

	@Override
	public ICAdjustment getICAdjustment(ICAdjustment adj, MeDataSource dataSource) {
		try (Connection con = DBConnection.getConnection(dataSource)){			
			String sql = "{call ame_sale_list_by_id(?)}";
			CallableStatement c = con.prepareCall(sql);
			c.setString(1, adj.getAdjId());
			ResultSet rs  = c.executeQuery();			
			ICAdjustment adjustment = null;
			ICAdjustmentDetail adjDetail = null;
			ArrayList<ICAdjustmentDetail> adjs = new ArrayList<>();			
			int i=0;
			while(rs.next()){ i++;			
				if(i==1){
					adjustment = new ICAdjustment();
					adjustment.setAdjId(rs.getString("AdjID"));
					adjustment.setAdjDate(rs.getDate("AdjDate"));
					adjustment.setAdjReference(rs.getString("AdjReference"));
					adjustment.setAdjDesc(rs.getString("AdjDesc"));
					adjustment.setPostStatus(rs.getString("PostStatus"));
				}
				adjDetail = new ICAdjustmentDetail();
				adjDetail.setAdjId(rs.getString("AdjID"));
				adjDetail.setLineNo(rs.getInt("LineNo"));
				adjDetail.setItem(new Item(rs.getString("ItemID"), rs.getString("ItemName")));
				adjDetail.setLocation(new Location(rs.getString("LocationID"), ""));
				adjDetail.setBatchNo(rs.getInt("BatchNo"));
				adjDetail.setAdjQty(rs.getDouble("AdjQty"));
				adjDetail.setUom(new Uom(rs.getString("UomID"), ""));
				adjDetail.setAdjCost(rs.getDouble("AdjCost"));
				adjDetail.setAmount(rs.getDouble("Amount"));
				adjDetail.setClassCode(new Class(rs.getString("ClassID"), "", 0));
				adjs.add(adjDetail);				
			}
			adjustment.setAdjDetail(adjs);
			return adjustment;
		}catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}

}
