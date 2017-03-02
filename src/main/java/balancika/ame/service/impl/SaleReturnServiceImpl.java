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
import balancika.ame.entities.tansaction.SaleReturn;
import balancika.ame.entities.tansaction.SaleReturnDetail;
import balancika.ame.service.SaleReturnService;
import balancika.ame.utilities.DBConnection;

@Repository
public class SaleReturnServiceImpl implements SaleReturnService{

	@Override
	public SaleReturn getSaleReturn(SaleReturn sr, MeDataSource dataSource) {
		try (Connection con = DBConnection.getConnection(dataSource)){			
			String sql = "{call ame_sale_return_list_by_id(?)}";
			CallableStatement c = con.prepareCall(sql);
			c.setString(1, sr.getSaleReturnId());
			ResultSet rs  = c.executeQuery();			
			SaleReturn sales = null;
			SaleReturnDetail saleDetail = null;
			ArrayList<SaleReturnDetail> saleDetails = new ArrayList<>();
			int i=0;
			while(rs.next()){ i++;			
				if(i==1){
					sales = new SaleReturn();
					sales.setSaleReturnId(rs.getString("RetID"));
					sales.setSaleId(rs.getString("SalID"));
					sales.setSaleReference(rs.getString("SalReference"));
					sales.setSaleType(rs.getString("SalType"));
					sales.setSaleDate(rs.getDate("SalDate"));
					sales.setPostStatus(rs.getString("PostStatus"));
					sales.setRemark(rs.getString("Remark"));
					sales.setTotalAmount(rs.getDouble("TotalAmt"));
					sales.setDisInvDol(rs.getDouble("DisInvDol"));
					sales.setDisInvPer(rs.getDouble("DisInvPer"));
					sales.setTotalDis(rs.getDouble("TotalDis"));
					sales.setTotalSTax(rs.getDouble("TotalSTax"));
					sales.setTotalVTax(rs.getDouble("TotalVTax"));
					sales.setNetTotalAmt(rs.getDouble("NetTotalAmt"));
					sales.setDueDate(rs.getDate("DueDate"));
					sales.setShipTo(rs.getString("ShipTo"));
					sales.setClassCode(new Class(rs.getString("ClassID"), rs.getString("ClassName"), 0));
					sales.setPriceCode(new PriceCode(rs.getString("PriceCode"), ""));
					sales.setCustomer(new Customer(rs.getString("CustID"), rs.getString("CustName")));
					sales.setEmployee(new Employee(rs.getString("EmpID"), rs.getString("EmpName")));
				}
				saleDetail = new SaleReturnDetail();
				saleDetail.setSaleReturnId(rs.getString("RetID"));
				saleDetail.setLineNo(rs.getInt("LineNo"));
				saleDetail.setItem(new Item(rs.getString("ItemID"), rs.getString("ItemName")));
				saleDetail.setLocation(new Location(rs.getString("LocationID"), ""));
				saleDetail.setClassCode(new Class(rs.getString("ClassDetailID"), "", 0));
				saleDetail.setUom(new Uom(rs.getString("UomID"), ""));
				saleDetail.setQty(rs.getDouble("RetQty"));
				saleDetail.setUnitPrice(rs.getDouble("UnitPrice"));
				saleDetail.setTotalAmt(rs.getDouble("TotalAmt"));
				saleDetail.setFactor(rs.getDouble("Factor"));
				saleDetail.setDisDol(rs.getDouble("DisDol"));
				saleDetail.setDisPer(rs.getDouble("DisPer"));
				saleDetail.setSTaxDol(rs.getDouble("STaxDol"));
				saleDetail.setSTaxPer(rs.getDouble("STaxPer"));
				saleDetail.setVTaxDol(rs.getDouble("VTaxDol"));
				saleDetail.setVTaxPer(rs.getDouble("VTaxPer"));
				saleDetail.setNetTotalAmt(rs.getDouble("NetTotalAmtDetail"));
				saleDetail.setSalQty(rs.getDouble("SalQty"));
				saleDetails.add(saleDetail);
			}
			sales.setSaleReturnDetail(saleDetails);
			return sales;
		}catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	
}
