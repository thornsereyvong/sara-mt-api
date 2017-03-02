package balancika.ame.service.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.maintain.Customer;
import balancika.ame.entities.maintain.Employee;
import balancika.ame.entities.maintain.Item;
import balancika.ame.entities.setting.Class;
import balancika.ame.entities.setting.Location;
import balancika.ame.entities.setting.PriceCode;
import balancika.ame.entities.setting.Uom;
import balancika.ame.entities.tansaction.CreditNote;
import balancika.ame.entities.tansaction.CreditNoteDetail;
import balancika.ame.service.CreditNoteService;
import balancika.ame.utilities.DBConnection;

@Repository
public class CreditNoteServiceImpl implements CreditNoteService{

	@Override
	public CreditNote getCreditNote(CreditNote cdn, MeDataSource dataSource) {
		try (Connection con = DBConnection.getConnection(dataSource)){			
			String sql = "{call ame_credit_note_list_by_id(?)}";
			CallableStatement c = con.prepareCall(sql);
			c.setString(1, cdn.getEntryId());
			ResultSet rs  = c.executeQuery();	
			CreditNote creditNote = null;
			CreditNoteDetail cdnDetail = null;
			ArrayList<CreditNoteDetail> cdnDetails = new ArrayList<>();
			int i=0;
			while(rs.next()){ i++;			
				if(i==1){
					creditNote = new CreditNote();
					creditNote.setEntryId(rs.getString("entryId"));
					creditNote.setReference(rs.getString("CrRef"));
					creditNote.setCreditNoteDate(rs.getDate("CrDate"));
					creditNote.setPostStatus(rs.getString("PostStatus"));
					creditNote.setRemark(rs.getString("Remark"));
					creditNote.setTotalAmount(rs.getDouble("TotalAmt"));
					creditNote.setDisInvDol(rs.getDouble("DisInvDol"));
					creditNote.setDisInvPer(rs.getDouble("DisInvPer"));
					creditNote.setTotalDis(rs.getDouble("TotalDis"));
					creditNote.setTotalSTax(rs.getDouble("TotalSTax"));
					creditNote.setTotalVTax(rs.getDouble("TotalVTax"));
					creditNote.setNetTotalAmt(rs.getDouble("NetTotalAmt"));
					creditNote.setShipTo(rs.getString("ShipTo"));
					creditNote.setClassCode(new Class(rs.getString("ClassID"), rs.getString("ClassName"), 0));
					creditNote.setPriceCode(new PriceCode(rs.getString("PriceCode"), ""));
					creditNote.setCustomer(new Customer(rs.getString("CustID"), rs.getString("CustName")));
					creditNote.setEmployee(new Employee(rs.getString("EmpID"), rs.getString("EmpName")));
				}
				cdnDetail = new CreditNoteDetail();
				cdnDetail.setEntryId(rs.getString("entryId"));
				cdnDetail.setLineNo(rs.getInt("LineNo"));
				cdnDetail.setItem(new Item(rs.getString("ItemID"), rs.getString("ItemName")));
				cdnDetail.setLocation(new Location(rs.getString("LocationID"), ""));
				cdnDetail.setClassCode(new Class(rs.getString("ClassDetailID"), "", 0));
				cdnDetail.setUom(new Uom(rs.getString("UomID"), ""));
				cdnDetail.setQty(rs.getDouble("CrQty"));
				cdnDetail.setUnitPrice(rs.getDouble("CrAmt"));
				cdnDetail.setTotalAmt(rs.getDouble("TotalAmtDetail"));
				cdnDetail.setFactor(rs.getDouble("Factor"));
				cdnDetail.setDisDol(rs.getDouble("DisDol"));
				cdnDetail.setDisPer(rs.getDouble("DisPer"));
				cdnDetail.setSTaxDol(rs.getDouble("STaxDol"));
				cdnDetail.setSTaxPer(rs.getDouble("STaxPer"));
				cdnDetail.setVTaxDol(rs.getDouble("VTaxDol"));
				cdnDetail.setVTaxPer(rs.getDouble("VTaxPer"));
				cdnDetail.setNetTotalAmt(rs.getDouble("NetTotalAmtDetail"));
				cdnDetails.add(cdnDetail);
			}
			creditNote.setCreditNoteDetail(cdnDetails);
			return creditNote;
		}catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}

}
