package balancika.ame.service.impl;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.tansaction.Transaction;
import balancika.ame.service.PostTransactionService;
import balancika.ame.utilities.DBConnection;

@Repository
public class PostTransactionServiceImpl implements PostTransactionService{
	
	@Override
	public List<Transaction> listTransaction(Transaction tran, MeDataSource dataSource){
		Transaction trans = null;		
		try (Connection con = DBConnection.getConnection(dataSource)){
			CallableStatement cstmt = null;
			String search = tran.getTransType()+" : "+tran.getIsVoid()+" : "+tran.getSearchClick()+" : "+tran.getFromDate()+" : "+tran.getToDate()+" : "+tran.getFilterType()+" : "+tran.getSearch();		
			String sql = "{call ame_spLoad_PostTrans(?)}";
			cstmt = (CallableStatement) con.prepareCall(sql);
			cstmt.setString(1, search);			
			ResultSet rs = cstmt.executeQuery();
			ArrayList<Transaction> arrTran = new ArrayList<Transaction>();
			while(rs.next()){
				trans = new Transaction();
				trans.setTransId(rs.getString("Trans. ID"));
				trans.setTransDate(rs.getString("Trans. Date"));
				trans.setTransName(rs.getString("transName"));
				trans.setTransReference(rs.getString("Reference"));
				trans.setTransRemark(rs.getString("Remark"));
				trans.setTransAmt(rs.getDouble("Total Amount"));
				trans.setTransStatus(rs.getString("Post Status"));
				arrTran.add(trans);
			}		
			return arrTran;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Transaction> listTransFTDate(MeDataSource dataSource){		
		try (Connection con = DBConnection.getConnection(dataSource)){
			CallableStatement cstmt = null;		
			String sql = "{call ame_post_trans_FTDate()}";
			cstmt = (CallableStatement) con.prepareCall(sql);		
			ResultSet rs = cstmt.executeQuery();
			ArrayList<Transaction> arrTran = new ArrayList<Transaction>();
			Transaction trans = null;
			while(rs.next()){
				trans = new Transaction();
				trans.setTransType(rs.getString("Module"));
				trans.setFromDate(rs.getString("FromDate"));
				trans.setToDate(rs.getString("ToDate"));
				arrTran.add(trans);
			}			
			return arrTran;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean checkExist(String sql, MeDataSource dataSource){
		try (Connection con = DBConnection.getConnection(dataSource)){
			Statement stmt=con.createStatement();  
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				return true;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkPostJournal(Transaction tran, MeDataSource dataSource){		
		try (Connection con = DBConnection.getConnection(dataSource)){
			String sql = "SELECT COUNT(*) CRow FROM tblAccount WHERE AccID in (SELECT AccID FROM tblJournalDetails WHERE JID = ?)";
			PreparedStatement p = (PreparedStatement) con.prepareStatement(sql);
			p.setString(1, tran.getTransId());
			ResultSet rs = p.executeQuery(sql);
			while(rs.next()){
				if(rs.getInt("CRow")>= 1){
					return true;
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean checkDrCr(Transaction tran, MeDataSource dataSource){		
		try (Connection con = DBConnection.getConnection(dataSource)){
			String sql = "SELECT SUM(CAST(COALESCE(Amount,0) AS DECIMAL(16,2))) as 'CTotal' FROM tblJournalDetails WHERE JID=?";
			PreparedStatement p = (PreparedStatement) con.prepareStatement(sql);
			p.setString(1, tran.getTransId());
			ResultSet rs = p.executeQuery(sql);
			while(rs.next()){
				if(rs.getDouble("CTotal") == 0.00){
					return true;
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String checkReference(String rType, String rId, MeDataSource dataSource){
		String msg = "";
		try (Connection con = DBConnection.getConnection(dataSource)){			
			String sql = "{call ame_post_check_reference(?,?)}";
			CallableStatement cstmt = (CallableStatement) con.prepareCall(sql);
			cstmt.setString(1, rType);
			cstmt.setString(2, rId);
			ResultSet rs = cstmt.executeQuery();
			int i=0;
			while(rs.next()){i++;
				msg +=i+". "+"Module: "+rs.getString("DocType")+" and ID: "+ rs.getString("DocID")+".\n";
			}			
			if(!msg.equals("")){
				msg = "Operation not allowed. The transaction in module: "+rType+" with id: "+rId+" is link to:\n"+msg;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public boolean checkLockPeriod(Transaction tran, MeDataSource dataSource){
		try (Connection con = DBConnection.getConnection(dataSource)){			
			String sql = "{call ame_post_check_lock_period(?)}";
			CallableStatement cstmt = (CallableStatement) con.prepareCall(sql);
			cstmt.setString(1, tran.getTransDate());
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				if(rs.getInt("CRow")>= 1){
					return true;
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean voidTransByTransId(String sql, String transId,int clone, MeDataSource dataSource) {
		try (Connection con = DBConnection.getConnection(dataSource)){						
			CallableStatement cstmt = (CallableStatement) con.prepareCall(sql);
			cstmt.setString(1, transId);
			cstmt.setInt(2, clone);
			cstmt.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkLine(Transaction tran, MeDataSource dataSource) {
		try (Connection con = DBConnection.getConnection(dataSource)){
			String sql = "SELECT AccID FROM tblJournalDetails WHERE JID = ?";
			PreparedStatement p = (PreparedStatement) con.prepareStatement(sql);
			p.setString(1, tran.getTransId());
			ResultSet rs = p.executeQuery(sql);
			int i=0;
			while(rs.next()){
				i++;
				if(i >= 2)
					return true;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean postTransByTransId(String sql, String transId, MeDataSource dataSource) {
		try (Connection con = DBConnection.getConnection(dataSource)){						
			CallableStatement cstmt = (CallableStatement) con.prepareCall(sql);
			cstmt.setString(1, transId);
			cstmt.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
