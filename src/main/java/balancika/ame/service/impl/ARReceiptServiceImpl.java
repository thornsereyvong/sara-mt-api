package balancika.ame.service.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.maintain.Customer;
import balancika.ame.entities.setting.Account;
import balancika.ame.entities.setting.Class;
import balancika.ame.entities.tansaction.ARReceipt;
import balancika.ame.entities.tansaction.ARReceiptDetail;
import balancika.ame.entities.tansaction.Sale;
import balancika.ame.service.ARReceiptService;
import balancika.ame.utilities.DBConnection;

@Repository
public class ARReceiptServiceImpl implements ARReceiptService{

	@Override
	public ARReceipt getARReceipt(ARReceipt rcp, MeDataSource dataSource) {
		try (Connection con = DBConnection.getConnection(dataSource)){
			
			String sql = "{call ame_ar_receipt_list_by_id(?)}";
			CallableStatement c = con.prepareCall(sql);
			c.setString(1, rcp.getRcpId());
			ResultSet rs  = c.executeQuery();			
			ARReceipt receipt = null;
			ARReceiptDetail rcpDetail = null;
			Sale sale = null;
			ArrayList<ARReceiptDetail> rcps = new ArrayList<>();			
			int i=0;
			while(rs.next()){ i++;
			
				if(i==1){
					receipt = new ARReceipt();
					receipt.setRcpId(rs.getString("RcpID"));
					receipt.setReference(rs.getString("RcpReference"));
					receipt.setRcpType(rs.getString("RcpType"));
					receipt.setRemark(rs.getString("Remark"));
					receipt.setClassCode(new Class(rs.getString("ClassID"), rs.getString("ClassName"), 0));
					receipt.setCustomer(new Customer(rs.getString("CustID"), rs.getString("CustName")));
					receipt.setAccount(new Account(rs.getString("AccId"),rs.getString("AccName")));
					receipt.setTotalDiscount(rs.getDouble("TotalDiscount"));
					receipt.setRcpDate(rs.getDate("RcpDate"));
					receipt.setTotalPmtAmount(rs.getDouble("TotalRcpAmount"));
					receipt.setPostStatus(rs.getString("PostStatus"));					
				}
				
				rcpDetail = new ARReceiptDetail();			
				sale = new Sale();
				sale.setSaleId(rs.getString("SalID"));
				sale.setSaleReference(rs.getString("SalReference"));
				sale.setSaleDate(rs.getDate("SalDate"));
				sale.setPmtToDate(rs.getDouble("PmtToDate"));
				sale.setNetTotalAmt(rs.getDouble("NetTotalAmt"));
				
				rcpDetail.setSale(sale);
				rcpDetail.setRcpId(rs.getString("RcpID"));
				rcpDetail.setTransDescription(rs.getString("transDescription"));
				rcpDetail.setDiscount(rs.getDouble("Discount"));
				rcpDetail.setPmtAmount(rs.getDouble("RcpAmount"));
				rcpDetail.setAppliedAmt(rs.getDouble("AppliedAmt"));
				rcpDetail.setAmountDue(rs.getDouble("AmountDue"));
				rcpDetail.setNetAmountDue(rs.getDouble("NetAmountDue"));
				
				rcps.add(rcpDetail);
			}
			receipt.setReceiptDetail(rcps);
			return receipt;	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
