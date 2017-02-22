package balancika.ame.service;

import java.util.List;
import org.springframework.stereotype.Repository;
import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.Transaction;

@Repository
public interface PostTransactionService {
	List<Transaction> listTransaction(Transaction tran, MeDataSource dataSource);
	List<Transaction> listTransFTDate(MeDataSource dataSource);
	String checkReference(String rType, String rId, MeDataSource dataSource);
	boolean checkExist(String sql, MeDataSource dataSource);
	boolean checkPostJournal(Transaction tran, MeDataSource dataSource);
	boolean checkDrCr(Transaction tran, MeDataSource dataSource);
	boolean checkLockPeriod(Transaction tran, MeDataSource dataSource);
	boolean checkLine(Transaction Tran, MeDataSource dataSource);
	boolean voidTransByTransId(String sql,String transId,int clone, MeDataSource dataSource);	
	boolean postTransByTransId(String sql,String transId, MeDataSource dataSource);	
}
