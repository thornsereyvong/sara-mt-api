package balancika.ame.service.pos.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

import balancika.ame.service.pos.POSTransactionService;
import balancika.ame.utilities.DBConnection;
import balancika.ame.utilities.POSFilter;
import balancika.ame.utilities.POSForm;
import balancika.ame.utilities.SQLUtil;

@Repository
public class POSTransactionServiceImpl implements POSTransactionService{

	@Override
	public List<Map<String, Object>> listSaleOrder(POSFilter pos) {
		try (Connection con = DBConnection.getConnection(pos.getDataSource())){						
			String sql = "call ame_res_list_sale_order_by_station(?)";
			CallableStatement c = con.prepareCall(sql);
			c.setString(1, pos.getFilter().get("stationId"));
			ResultSet rs = c.executeQuery();
			return SQLUtil.aliasRSToMap(rs);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addSaleOrder(POSForm frm) {
		if(frm.getRecords().size()<0) return false;
		String sql = "INSERT INTO tblres_temporder values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection con = DBConnection.getConnection(frm.getDataSource())){					
			con.setAutoCommit(false);
			PreparedStatement pstmt = con.prepareStatement(sql);
			for(Map<String, Object> record : frm.getRecords()){
				pstmt.setObject(1, record.get("stationId"));
				pstmt.setObject(2, record.get("lineNo"));
				pstmt.setObject(3, record.get("itemId"));
				pstmt.setObject(4, record.get("itemName"));
				pstmt.setObject(5, record.get("salQty"));
				pstmt.setObject(6, record.get("uomId"));
				pstmt.setObject(7, record.get("unitPrice"));
				pstmt.setObject(8, record.get("totalAmt"));
				pstmt.setObject(9, record.get("discountpercent"));
				pstmt.setObject(10, record.get("discountDollar"));
				pstmt.setObject(11, record.get("netTotalAmtBeforeTax"));
				pstmt.setObject(12, record.get("sTaxPercent"));
				pstmt.setObject(13, record.get("sTaxDollar"));
				pstmt.setObject(14, record.get("vatPercent"));
				pstmt.setObject(15, record.get("vatDollar"));
				pstmt.setObject(16, record.get("taxDollar"));
				pstmt.setObject(17, record.get("netTotalAmtAfterTax"));
				pstmt.setObject(18, record.get("qtySentToKitchen"));
				pstmt.setObject(19, record.get("isRentalItem"));
				pstmt.setObject(20, record.get("billNum"));
				pstmt.setObject(21, record.get("openItemDes"));
				pstmt.setObject(22, record.get("serviceChargePer"));
				pstmt.setObject(23, record.get("serviceChargeDol"));
				pstmt.addBatch();
			}			
			int [] updateCounts = pstmt.executeBatch();
			if(frm.getRecords().size() == updateCounts.length){
				con.commit();
				con.setAutoCommit(true);
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteSaleOrderByStation(POSForm frm) {
		try (Connection con = DBConnection.getConnection(frm.getDataSource())){						
			String sql = "DELETE FROM tblres_temporder WHERE stationid=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setObject(1, frm.getRecords().get(0).get("stationId"));
			pstmt.executeUpdate();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateSaleOrderByStation(POSForm frm) {
		try{						
			if(this.deleteSaleOrderByStation(frm))
				if(this.addSaleOrder(frm))
					return true;			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
