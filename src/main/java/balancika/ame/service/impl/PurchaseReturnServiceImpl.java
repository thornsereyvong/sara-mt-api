package balancika.ame.service.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.maintain.Item;
import balancika.ame.entities.maintain.Vendor;
import balancika.ame.entities.setting.Class;
import balancika.ame.entities.setting.Location;
import balancika.ame.entities.setting.Uom;
import balancika.ame.entities.tansaction.PurchaseReturn;
import balancika.ame.entities.tansaction.PurchaseReturnDetail;
import balancika.ame.service.PurchaseReturnService;
import balancika.ame.utilities.DBConnection;

@Repository
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
			while(rs.next()){ i++;
				if(i==1){
					purchaseReturn = new PurchaseReturn();
					purchaseReturn.setPurchaseReturnId(rs.getString("RetID"));
					purchaseReturn.setPurchaseId(rs.getString("PurID"));
					purchaseReturn.setReference(rs.getString("RetReference"));
					purchaseReturn.setPurchaseReturnDate(rs.getDate("RetDate"));
					purchaseReturn.setPostStatus(rs.getString("PostStatus"));
					purchaseReturn.setRemark(rs.getString("Remark"));
					purchaseReturn.setVendor(new Vendor(rs.getString("VendID"), rs.getString("VendName")));
					purchaseReturn.setClassCode(new Class(rs.getString("ClassID"), rs.getString("ClassName"), 0));
					purchaseReturn.setDisInvDol(rs.getDouble("DisInvDol"));
					purchaseReturn.setDisInvPer(rs.getDouble("DisInvPer"));
					purchaseReturn.setTotalDis(rs.getDouble("TotalDis"));
					purchaseReturn.setTotalST(rs.getDouble("TotalSTax"));
					purchaseReturn.setTotalVAT(rs.getDouble("TotalVTax"));
					purchaseReturn.setNetTotalAmt(rs.getDouble("NetTotalAmt"));
					purchaseReturn.setTotalTax(rs.getDouble("TotalTax"));
					purchaseReturn.setTotalAmt(rs.getDouble("RetTotalAmt"));
					purchaseReturn.setTotalAppPay(rs.getDouble("RetToDate"));
					purchaseReturn.setNetTotalInvoice(rs.getDouble("NetInvoice"));
				}
				purchaseReturnDetail = new PurchaseReturnDetail();
				purchaseReturnDetail.setPurchaseReturnId(rs.getString("RetID"));
				purchaseReturnDetail.setLineNo(rs.getInt("LineNo"));
				purchaseReturnDetail.setItem(new Item(rs.getString("ItemID"), rs.getString("ItemName")));
				purchaseReturnDetail.setLocation(new Location(rs.getString("LocationID"), ""));
				purchaseReturnDetail.setClassCode(new Class(rs.getString("ClassDetailID"), "", 0));
				purchaseReturnDetail.setUom(new Uom(rs.getString("UomID"), ""));
				purchaseReturnDetail.setRetQty(rs.getDouble("RetQty"));
				purchaseReturnDetail.setUnitCost(rs.getDouble("UnitCost"));
				purchaseReturnDetail.setTotalCost(rs.getDouble("TotalCost"));
				purchaseReturnDetail.setFactor(rs.getDouble("Factor"));
				purchaseReturnDetail.setDisDol(rs.getDouble("DisDol"));
				purchaseReturnDetail.setDisPer(rs.getDouble("DisPer"));
				purchaseReturnDetail.setStaxDol(rs.getDouble("STaxDol"));
				purchaseReturnDetail.setStaxPer(rs.getDouble("STaxPer"));
				purchaseReturnDetail.setVatDol(rs.getDouble("VTaxDol"));
				purchaseReturnDetail.setVatPer(rs.getDouble("VTaxPer"));
				purchaseReturnDetail.setNetTotalAmt(rs.getDouble("NetTotalAmtDetail"));
				purchaseReturnArr.add(purchaseReturnDetail);
			}
			purchaseReturn.setPurchaseReturnDetail(purchaseReturnArr);
			return purchaseReturn;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
