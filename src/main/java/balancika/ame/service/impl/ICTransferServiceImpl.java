package balancika.ame.service.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.maintain.Customer;
import balancika.ame.entities.maintain.Item;
import balancika.ame.entities.setting.Location;
import balancika.ame.entities.setting.Uom;
import balancika.ame.entities.tansaction.ICTransfer;
import balancika.ame.entities.tansaction.ICTransferDetail;
import balancika.ame.service.ICTransferService;
import balancika.ame.utilities.DBConnection;

@Repository
public class ICTransferServiceImpl implements ICTransferService{

	@Override
	public ICTransfer getICTransfer(ICTransfer trf, MeDataSource dataSource) {
		
		try (Connection con = DBConnection.getConnection(dataSource)){			
			String sql = "{call ame_ic_transfer_list_by_id(?)}";
			CallableStatement c = con.prepareCall(sql);
			c.setString(1, trf.getTrfId());
			ResultSet rs  = c.executeQuery();
			ICTransfer transfer  = null;
			ICTransferDetail trfDetail = null;
			ArrayList<ICTransferDetail> trfs = new ArrayList<>();
			int i=0;
			while(rs.next()){ i++;			
				if(i==1){
					transfer = new ICTransfer();
					transfer.setTrfId(rs.getString("TrfID"));
					transfer.setTrfDate(rs.getDate("TrfDate"));
					transfer.setTrfReference(rs.getString("TrfReference"));
					transfer.setConsignment(rs.getInt("Consignment"));
					transfer.setPostStatus(rs.getString("PostStatus"));
					transfer.setTrfDescription(rs.getString("TrfDesc"));
					transfer.setpWholeSale(rs.getString("PWholesale"));
					transfer.setpRetail(rs.getString("PRetail"));
					transfer.setCustomer(new Customer(rs.getString("CustID"), rs.getString("CustName")));
					transfer.setLocation(new Location(rs.getString("LocationID"), rs.getString("LocationName")));										
				}
				
				trfDetail = new ICTransferDetail();
				trfDetail.setTrfId(rs.getString("TrfID"));
				trfDetail.setLineNo(rs.getInt("LineNo"));
				trfDetail.setItem(new Item(rs.getString("ItemID"), rs.getString("ItemName")));
				trfDetail.setfLocation(new Location(rs.getString("FromLoc"), ""));
				trfDetail.settLocation(new Location(rs.getString("ToLoc"), ""));
				trfDetail.setUom(new Uom(rs.getString("UOMID"), ""));
				trfDetail.setTrfQty(rs.getDouble("TrfQty"));
				trfDetail.setRetailPrice(rs.getDouble("RetailPrice"));
				trfDetail.setWholeSalePrice(rs.getDouble("WholesalePrice"));
				trfs.add(trfDetail);				
			}
			transfer.setTrfDetail(trfs);
			return transfer;
		}catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}

}
