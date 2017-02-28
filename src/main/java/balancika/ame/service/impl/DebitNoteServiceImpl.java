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
import balancika.ame.entities.tansaction.DebitNote;
import balancika.ame.entities.tansaction.DebitNoteDetail;
import balancika.ame.service.DebitNoteService;
import balancika.ame.utilities.DBConnection;

@Repository
public class DebitNoteServiceImpl implements DebitNoteService{

	@Override
	public DebitNote getDebitNote(DebitNote debitNote, MeDataSource dataSource) {
		try (Connection con = DBConnection.getConnection(dataSource)){
			String sql = "{call ame_debit_note_list_by_id(?)}";
			CallableStatement c = con.prepareCall(sql);
			c.setString(1, debitNote.getEntryId());
			ResultSet rs  = c.executeQuery();
			
			DebitNote dn = null;
			DebitNoteDetail dnd = null;
			ArrayList<DebitNoteDetail> dndArr = new ArrayList<>();
			int i=0;
			while(rs.next()){ i++;
				if(i==1){
					dn = new DebitNote();
					dn.setEntryId(rs.getString("entryId"));
					dn.setReference(rs.getString("DrRef"));
					dn.setRemark(rs.getString("Remark"));
					dn.setDebitNoteDate(rs.getDate("DrDate"));
					dn.setPostStatus(rs.getString("PostStatus"));
					dn.setVendor(new Vendor(rs.getString("VendID"), rs.getString("VendName")));
					dn.setClassCode(new Class(rs.getString("ClassID"), rs.getString("ClassName"), 0));
					dn.setDisInvDol(rs.getDouble("DisInvDol"));
					dn.setDisInvPer(rs.getDouble("DisInvPer"));
					dn.setTotalDis(rs.getDouble("TotalDis"));
					dn.setTotalST(rs.getDouble("TotalSTax"));
					dn.setTotalVAT(rs.getDouble("TotalVTax"));
					dn.setNetTotalAmt(rs.getDouble("NetTotalAmt"));
					dn.setTotalTax(rs.getDouble("TotalTax"));
					dn.setTotalAmt(rs.getDouble("TotalAmt"));
					dn.setTotalAppPay(rs.getDouble("PmtToDate"));
					dn.setNetTotalInvoice(rs.getDouble("NetInvoice"));
				}
				
				dnd = new DebitNoteDetail();
				dnd.setEntryId(rs.getString("entryId"));
				dnd.setLineNo(rs.getInt("LineNo"));
				dnd.setItem(new Item(rs.getString("ItemID"), rs.getString("ItemName")));
				dnd.setLocation(new Location(rs.getString("LocationID"), ""));
				dnd.setClassCode(new Class(rs.getString("ClassDetailID"), "", 0));
				dnd.setUom(new Uom(rs.getString("UomID"), ""));
				dnd.setQty(rs.getDouble("DrQty"));
				dnd.setUnitCost(rs.getDouble("DrAmt"));
				dnd.setTotalCost(rs.getDouble("DrTotalAmt"));
				dnd.setFactor(rs.getDouble("Factor"));
				dnd.setDisDol(rs.getDouble("DisDol"));
				dnd.setDisPer(rs.getDouble("DisPer"));
				dnd.setStaxDol(rs.getDouble("STaxDol"));
				dnd.setStaxPer(rs.getDouble("STaxPer"));
				dnd.setVatDol(rs.getDouble("VTaxDol"));
				dnd.setVatPer(rs.getDouble("VTaxPer"));
				dnd.setNetTotalAmt(rs.getDouble("NetTotalAmtDetail"));
				dndArr.add(dnd);
			}			
			dn.setDebitNoteDetail(dndArr);
			return dn;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
