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
import balancika.ame.entities.tansaction.PurchaseInvoice;
import balancika.ame.entities.tansaction.PurchaseInvoiceDetail;
import balancika.ame.service.PurchaseInvoiceService;
import balancika.ame.utilities.DBConnection;

@Repository
public class PurchaseInvoiceServiceImpl implements PurchaseInvoiceService{
	
	@Override
	public PurchaseInvoice getPurchaseInvoice(PurchaseInvoice purchase, MeDataSource dataSource) {
		
		try (Connection con = DBConnection.getConnection(dataSource)){
			String sql = "{call ame_purchase_list_by_id(?)}";
			CallableStatement c = con.prepareCall(sql);
			c.setString(1, purchase.getPurchaseId());
			ResultSet rs  = c.executeQuery();
			PurchaseInvoice pur = null;
			int i=0;
			ArrayList<PurchaseInvoiceDetail> purDetailList = new ArrayList<>();
			PurchaseInvoiceDetail purDetail = null;
			while(rs.next()){i++;
				if(i==1){
					pur = new PurchaseInvoice();
					pur.setPurchaseId(rs.getString("PurID"));
					pur.setReference(rs.getString("PurReference"));
					pur.setPurchaseDate(rs.getDate("PurDate"));
					pur.setDueDate(rs.getDate("DueDate"));
					pur.setRemark(rs.getString("remark"));
					pur.setPostStatus(rs.getString("PostStatus"));
					pur.setPurchaseType(rs.getString("PurType"));
					pur.setDisInvDol(rs.getDouble("DisInvDol"));
					pur.setDisInvPer(rs.getDouble("DisInvPer"));
					pur.setTotalDis(rs.getDouble("TotalDis"));
					pur.setTotalST(rs.getDouble("TotalSTax"));
					pur.setTotalVAT(rs.getDouble("TotalVTax"));
					pur.setNetTotalAmt(rs.getDouble("NetTotalAmt"));
					pur.setTotalTax(rs.getDouble("TotalTax"));
					pur.setClassCode(new Class(rs.getString("ClassID"), rs.getString("ClassName"), 0));
					pur.setVendor(new Vendor(rs.getString("vendID"), rs.getString("VendName")));
				}
				purDetail = new PurchaseInvoiceDetail();
				purDetail.setPurchaseId(rs.getString("PurID"));
				purDetail.setLineNo(rs.getInt("LineNo"));
				purDetail.setItem(new Item(rs.getString("ItemID"), rs.getString("ItemName")));
				purDetail.setLocation(new Location(rs.getString("LocationID"), ""));
				purDetail.setClassCode(new Class(rs.getString("ClassDetailID"), "", 0));
				purDetail.setUom(new Uom(rs.getString("UomID"), ""));
				purDetail.setPurQty(rs.getDouble("PurQty"));
				purDetail.setUnitCost(rs.getDouble("UnitCost"));
				purDetail.setTotalCost(rs.getDouble("TotalCost"));
				purDetail.setFactor(rs.getDouble("Factor"));
				purDetail.setDisDol(rs.getDouble("DisDol"));
				purDetail.setDisPer(rs.getDouble("DisPer"));
				purDetail.setStaxDol(rs.getDouble("STaxDol"));
				purDetail.setStaxPer(rs.getDouble("STaxPer"));
				purDetail.setVatDol(rs.getDouble("VTaxDol"));
				purDetail.setVatPer(rs.getDouble("VTaxPer"));
				purDetail.setDisDol(rs.getDouble("NetTotalAmtDetail"));
				purDetailList.add(purDetail);
			}
			pur.setPurchaseDetail(purDetailList);
			return pur;
		}catch (Exception e) {
			e.printStackTrace();
			
		}		
		return null;
	}

}
