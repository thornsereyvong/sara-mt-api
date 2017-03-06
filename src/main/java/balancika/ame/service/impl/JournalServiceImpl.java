package balancika.ame.service.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.setting.Account;
import balancika.ame.entities.setting.Class;
import balancika.ame.entities.tansaction.Journal;
import balancika.ame.entities.tansaction.JournalDetail;
import balancika.ame.service.JournalService;
import balancika.ame.utilities.DBConnection;

@Repository
public class JournalServiceImpl implements JournalService{

	@Override
	public Journal getJournal(Journal jn, MeDataSource dataSource) {
		try (Connection con = DBConnection.getConnection(dataSource)){			
			String sql = "{call ame_journal_list_by_id(?)}";
			CallableStatement c = con.prepareCall(sql);
			c.setInt(1, jn.getjId());
			ResultSet rs  = c.executeQuery();
			Journal j = null;
			JournalDetail jndetail = null;
			ArrayList<JournalDetail> jns = new ArrayList<>();
			int i=0;
			while(rs.next()){ i++;
			
				if(i==1){
					j = new Journal();
					j.setjId(rs.getInt("JID"));
					j.setjDate(rs.getDate("JDate"));
					j.setReference(rs.getString("JReference"));
					j.setRemark(rs.getString("JRemark"));
					j.setPostStatus(rs.getString("PostStatus"));
					j.setType(rs.getString("JType"));
					j.setTotal(rs.getDouble("JTotal"));
					j.setPeriodM(rs.getInt("PeriodM"));
					j.setPeroidY(rs.getInt("PeroidY"));
				}
				
				jndetail = new JournalDetail();
				jndetail.setjId(rs.getInt("JID"));
				jndetail.setAccount(new Account(rs.getString("AccID"), rs.getString("AccName")));
				jndetail.setClassCode(new Class(rs.getString("ClassID"), "", 0));
				jndetail.setjDate(rs.getDate("TranDate"));
				jndetail.setReference(rs.getString("TranRef"));
				jndetail.setDrCr(rs.getString("DrCr"));
				jndetail.setAmount(rs.getDouble("Amount"));
				jns.add(jndetail);
			}
			
			if(j != null)
				j.setJournalDetail(jns);
			
			return j;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
