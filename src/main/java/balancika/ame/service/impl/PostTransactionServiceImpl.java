package balancika.ame.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.Transaction;
import balancika.ame.service.PostTransactionService;
import balancika.ame.utilities.DBConnection;

@Repository
public class PostTransactionServiceImpl implements PostTransactionService{
	
	@Transactional
	@Override
	public List<Transaction> listTransaction(Transaction tran, MeDataSource dataSource) throws SQLException {
		Transaction trans = null;
		CallableStatement cstmt = null;
		try (Connection con = DBConnection.getConnection(dataSource)){
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
			rs.close();			
			return arrTran;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 cstmt.close();
		}
		return null;
	}

	@Override
	public List<Transaction> listTransFTDate(MeDataSource dataSource) throws SQLException {
		CallableStatement cstmt = null;
		try (Connection con = DBConnection.getConnection(dataSource)){
					
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
			rs.close();			
			return arrTran;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 cstmt.close();
		}
		return null;
	}

}
