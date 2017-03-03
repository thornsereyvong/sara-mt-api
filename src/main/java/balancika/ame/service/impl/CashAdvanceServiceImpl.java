package balancika.ame.service.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.maintain.Employee;
import balancika.ame.entities.setting.Account;
import balancika.ame.entities.setting.Class;
import balancika.ame.entities.tansaction.CashAdvance;
import balancika.ame.service.CashAdvanceService;
import balancika.ame.utilities.DBConnection;

@Repository
public class CashAdvanceServiceImpl implements CashAdvanceService{

	@Override
	public CashAdvance getCashAdvance(CashAdvance ca, MeDataSource dataSource) {

		try (Connection con = DBConnection.getConnection(dataSource)){			
			String sql = "{call ame_cash_advance_list_by_id(?)}";
			CallableStatement c = con.prepareCall(sql);
			c.setString(1, ca.getCaId());
			ResultSet rs  = c.executeQuery();
			CashAdvance cashAdvance = null;
			while(rs.next()){			
				cashAdvance = new CashAdvance();
				cashAdvance.setCaId(rs.getString("CAID"));
				cashAdvance.setCaReference(rs.getString("CAReference"));
				cashAdvance.setCaDate(rs.getDate("Date"));
				cashAdvance.setExpectedClearDate(rs.getDate("Expected_Clear_date"));
				cashAdvance.setRemark(rs.getString("Purpose"));
				cashAdvance.setPostStatus(rs.getString("PostStatus"));
				cashAdvance.setEmployee(new Employee(rs.getString("EmpID"), rs.getString("EmpName")));
				cashAdvance.setCashAccount(new Account(rs.getString("Cash_Acc"), rs.getString("Cash_AccName")));
				cashAdvance.setCashAdvanceAccount(new Account(rs.getString("Cash_Advance_Acc"), rs.getString("Cash_Advance_AccName")));
				cashAdvance.setClassCode(new Class(rs.getString("ClassID"), rs.getString("ClassName"), 0));
				cashAdvance.setAmount(rs.getDouble("Amount"));
				
			}	
			return cashAdvance;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
