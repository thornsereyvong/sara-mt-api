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
import balancika.ame.entities.PostTransactionFrm;
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

	@Override
	public boolean voidTrans(PostTransactionFrm trans, MeDataSource dataSource) throws SQLException {
		CallableStatement cstmt = null;
		try (Connection con = DBConnection.getConnection(dataSource)){
			String sql = "";
			for(int i=0; i<trans.getTranss().size(); i++){
				switch(trans.getTransType()){  
					case "AP Invoice":
						sql = "{call spVoid_Purchase(?,0)}";
						break;
					case "AP Return Invoice": 
				    	sql = "{call spVoid_PurchaseReturn(?,0)}";
				    	break;
				    case "AP Debit Note":
				    	sql = "{call spVoid_DebitNote(?,0)}";
				    	break;
				    case "AP Payment":
				    	sql = "{call spVoid_Payment(?,0)}";
				    	break;
			    	case "AR Invoice":
			    		sql = "{call spVoid_Sale(?,0)}";
				    	break;
		    		case "AR Return Invoice":
		    			sql = "{call spVoid_SaleReturn(?,0)}"; 
				    	break;
		    		case "AR Credit Note":
		    			sql = "{call spVoid_CreditNote(?,0)}";
				    	break;
	    			case "AR Receipt":
	    				sql = "{call spVoid_Receipt(?,0)}"; 
				    	break;
	    			case "IC Transfer":
	    				sql = "{call spVoid_Transfer(?,0)}";
				    	break;
	    			case "IC Adjustment":
	    				sql = "{call spVoid_Adjustment(?,0)}";
				    	break;
	    			case "Cash Transfer":
	    				sql = "{call spVoid_CashTransfer(?,0)}";
				    	break;
	    			case "Cash Advance Clearance":
	    				sql = "{call spVoid_CashAdvanceClearance(?,0)}";
				    	break;
	    			case "GL Entries":
	    				
				    	break;
				    default:
			    	
				} 
				if(!sql.equals("")){
					cstmt = (CallableStatement) con.prepareCall(sql);	
					cstmt.setString(1, trans.getTranss().get(i).getTransId());
					if(cstmt.executeUpdate()>0){
						System.out.println(trans.getTranss().get(i).getTransId()+" is successful void!");
					}else{
						System.out.println(trans.getTranss().get(i).getTransId()+" is unsuccessful void!");
					}
				}
			}
					
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 cstmt.close();
		}
		return false;
	}

	@Override
	public boolean checkExist(Transaction tran, MeDataSource dataSource) throws SQLException {
		CallableStatement cstmt = null;
		try (Connection con = DBConnection.getConnection(dataSource)){					
			String sql = "";			
			switch(tran.getTransType()){  
				case "AP Invoice":
					sql = "select";
					break;
				case "AP Return Invoice": 
			    	sql = "{call spVoid_PurchaseReturn(?,0)}";
			    	break;
			    case "AP Debit Note":
			    	sql = "{call spVoid_DebitNote(?,0)}";
			    	break;
			    case "AP Payment":
			    	sql = "{call spVoid_Payment(?,0)}";
			    	break;
		    	case "AR Invoice":
		    		sql = "{call spVoid_Sale(?,0)}";
			    	break;
	    		case "AR Return Invoice":
	    			sql = "{call spVoid_SaleReturn(?,0)}"; 
			    	break;
	    		case "AR Credit Note":
	    			sql = "{call spVoid_CreditNote(?,0)}";
			    	break;
				case "AR Receipt":
					sql = "{call spVoid_Receipt(?,0)}"; 
			    	break;
				case "IC Transfer":
					sql = "{call spVoid_Transfer(?,0)}";
			    	break;
				case "IC Adjustment":
					sql = "{call spVoid_Adjustment(?,0)}";
			    	break;
				case "Cash Transfer":
					sql = "SELECT COUNT(*) 'Exist' FROM tblMoney_Company_Transfer WHERE PostStatus = 'Posted' and TrID=?";
			    	break;
				case "Cash Advance Clearance":
					sql = "{call spVoid_CashAdvanceClearance(?,0)}";
			    	break;
				case "GL Entries":
					
			    	break;
			    default:
		    	
			} 
			
			
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
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 cstmt.close();
		}
		return false;
	}

	@Override
	public boolean checkPostJournal(Transaction tran, MeDataSource dataSource) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkDrCr(Transaction tran, MeDataSource dataSource) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String checkReference(Transaction tran, MeDataSource dataSource) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
