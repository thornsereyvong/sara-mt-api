package balancika.ame.service.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.setting.Account;
import balancika.ame.entities.setting.Class;
import balancika.ame.entities.setting.Company;
import balancika.ame.entities.tansaction.CashTransfer;
import balancika.ame.service.CashTransferService;
import balancika.ame.utilities.DBConnection;

@Repository
public class CashTransferServiceImpl implements CashTransferService{

	@Override
	public CashTransfer getCashTransfer(CashTransfer ctf, MeDataSource dataSource) {
		try (Connection con = DBConnection.getConnection(dataSource)){			
			String sql = "{call ame_cash_advance_list_by_id(?)}";
			CallableStatement c = con.prepareCall(sql);
			c.setString(1, ctf.getCtfId());
			ResultSet rs  = c.executeQuery();
			CashTransfer cashtransfer = null;
			while(rs.next()){	
				cashtransfer = new CashTransfer();
				cashtransfer.setCtfId(rs.getString("TRID"));
				cashtransfer.setReference(rs.getString("Reference"));
				cashtransfer.setCtfDate(rs.getDate("Date"));
				cashtransfer.setRemark(rs.getString("Remark"));
				cashtransfer.setCtfType(rs.getString("Type"));
				cashtransfer.setPostStatus(rs.getString("PostStatus"));
				cashtransfer.setClassCode(new Class(rs.getString("ClassID"), rs.getString("ClassName"), 0));
				cashtransfer.setCompany(new Company(rs.getString("Branch"), rs.getString("BranchName")));
				cashtransfer.setAccount(new Account(rs.getString("Bank_Account"), rs.getString("Bank_AccountName")));				
			}
			return cashtransfer;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
