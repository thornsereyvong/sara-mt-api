package balancika.ame.service.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.maintain.Vendor;
import balancika.ame.entities.setting.Account;
import balancika.ame.entities.setting.Class;
import balancika.ame.entities.tansaction.APPayment;
import balancika.ame.entities.tansaction.APPaymentDetail;
import balancika.ame.entities.tansaction.PurchaseInvoice;
import balancika.ame.service.APPaymentService;
import balancika.ame.utilities.DBConnection;

@Repository
public class APPaymentServiceImpl implements APPaymentService{

	@Override
	public APPayment getAPPayment(APPayment apPayment, MeDataSource dataSource) {
		try (Connection con = DBConnection.getConnection(dataSource)){
			
			String sql = "{call ame_ap_payment_list_by_id(?)}";
			CallableStatement c = con.prepareCall(sql);
			c.setString(1, apPayment.getPmtId());
			ResultSet rs  = c.executeQuery();
			APPayment payment = null;
			APPaymentDetail payDetail = null;
			PurchaseInvoice pur = null;
			ArrayList<APPaymentDetail> pays = new ArrayList<>();
			
			int i=0;
			while(rs.next()){ i++;
			
				if(i==1){
					payment = new APPayment();
					payment.setPmtId(rs.getString("PmtID"));
					payment.setReference(rs.getString("PmtReference"));
					payment.setPmtType(rs.getString("PmtType"));
					payment.setRemark(rs.getString("Remark"));
					payment.setClassCode(new Class(rs.getString("ClassID"), rs.getString("ClassName"), 0));
					payment.setVendor(new Vendor(rs.getString("vendID"), rs.getString("VendName")));
					payment.setAccount(new Account(rs.getString("AccId"),rs.getString("AccName")));
					payment.setTotalDiscount(rs.getDouble("TotalDiscount"));
					payment.setPmtDate(rs.getDate("PmtDate"));
					payment.setTotalPmtAmount(rs.getDouble("TotalPmtAmount"));
					payment.setPostStatus(rs.getString("PostStatus"));					
				}
				
				payDetail = new APPaymentDetail();				
				pur = new PurchaseInvoice();
				pur.setPurchaseId(rs.getString("PurID"));
				pur.setReference(rs.getString("PurReference"));
				pur.setPurchaseDate(rs.getDate("PurDate"));
				pur.setPmtToDate(rs.getDouble("PmtToDate"));
				pur.setNetTotalAmt(rs.getDouble("NetTotalAmt"));
				
				payDetail.setPurchase(pur);
				payDetail.setPmtId(rs.getString("PmtID"));
				payDetail.setTransDescription(rs.getString("transDescription"));
				payDetail.setDiscount(rs.getDouble("Discount"));
				payDetail.setPmtAmount(rs.getDouble("PmtAmount"));
				payDetail.setAppliedAmt(rs.getDouble("AppliedAmt"));
				payDetail.setAmountDue(rs.getDouble("AmountDue"));
				payDetail.setNetAmountDue(rs.getDouble("NetAmountDue"));
				
				pays.add(payDetail);
			}
			payment.setApPaymentDetail(pays);
			return payment;	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
