package balancika.ame.service.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.maintain.Employee;
import balancika.ame.entities.setting.Account;
import balancika.ame.entities.setting.Class;
import balancika.ame.entities.tansaction.CashAdvance;
import balancika.ame.entities.tansaction.CashAdvanceClearance;
import balancika.ame.entities.tansaction.CashAdvanceClearanceDetail;
import balancika.ame.service.CashAdvanceClearanceService;
import balancika.ame.utilities.DBConnection;

@Repository
public class CashAdvanceClearanceServiceImpl implements CashAdvanceClearanceService{

	@Override
	public CashAdvanceClearance getCashAdvanceClearance(CashAdvanceClearance cl, MeDataSource dataSource) {
		try (Connection con = DBConnection.getConnection(dataSource)){			
			String sql = "{call ame_cash_advance_list_by_id(?)}";
			CallableStatement c = con.prepareCall(sql);
			c.setString(1, cl.getClId());
			ResultSet rs  = c.executeQuery();
			CashAdvance cashAdvance = null;
			CashAdvanceClearance cash = null;
			CashAdvanceClearanceDetail cashDetail = null;
			ArrayList<CashAdvanceClearanceDetail> cashs= new ArrayList<>();
			int i=0;
			while(rs.next()){	i++;
				if(i==1){
					cash = new CashAdvanceClearance();
					cash.setClId(rs.getString("CLID"));
					cash.setClearDate(rs.getDate("Clear_Date"));
					cash.setReference(rs.getString("Clear_ref"));
					cash.setPostStatus(rs.getString("PostStatus"));
					cash.setAmount(rs.getDouble("Amount"));
					cash.setRemark(rs.getString("Remark"));
					cash.setClassCode(new Class(rs.getString("ClassID"), rs.getString("ClassName"), 0));
					cash.setEmployee(new Employee(rs.getString("EmpID"), rs.getString("EmpName")));
					cash.setAccount(new Account(rs.getString("CashAcc"), rs.getString("CashAccountName")));
					
				}
				cashDetail = new CashAdvanceClearanceDetail();
				cashDetail.setCrId(rs.getString("CLID"));
				cashDetail.setClassCode(new Class(rs.getString("ClassDID"), rs.getString("ClassDName"), 0));
				cashAdvance = new CashAdvance();
				cashAdvance.setCaId(rs.getString("CAID"));
				cashAdvance.setCaReference(rs.getString("CAReference"));
				cashAdvance.setCaDate(rs.getDate("Date"));
				cashAdvance.setAmount(rs.getDouble("CashAmount"));
				
			}
			
			
			return cash;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
