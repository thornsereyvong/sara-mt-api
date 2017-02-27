package balancika.ame.service.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.tansaction.PurchaseReturn;
import balancika.ame.entities.tansaction.PurchaseReturnDetail;
import balancika.ame.service.PurchaseReturnService;
import balancika.ame.utilities.DBConnection;

public class PurchaseReturnServiceImpl implements PurchaseReturnService{

	@Override
	public PurchaseReturn getPurchaseInvoice(PurchaseReturn purReturn, MeDataSource dataSource) {
		try (Connection con = DBConnection.getConnection(dataSource)){
			String sql = "{call ame_purchase_return_list_by_id(?)}";
			CallableStatement c = con.prepareCall(sql);
			c.setString(1, purReturn.getPurchaseReturnId());
			ResultSet rs  = c.executeQuery();
			PurchaseReturn purchaseReturn = null;
			PurchaseReturnDetail purchaseReturnDetail = null;
			ArrayList<PurchaseReturnDetail> purchaseReturnArr = new ArrayList<>();
			int i=0;
			while(rs.next()){i++;
				if(i==1){
					purchaseReturn = new PurchaseReturn();
				}
			}
			return purchaseReturn;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
