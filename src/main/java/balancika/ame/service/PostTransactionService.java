package balancika.ame.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.PostTransactionFrm;
import balancika.ame.entities.Transaction;

@Repository
public interface PostTransactionService {
	List<Transaction> listTransaction(Transaction tran, MeDataSource dataSource) throws SQLException;
	List<Transaction> listTransFTDate(MeDataSource dataSource) throws SQLException;
	boolean voidTrans(PostTransactionFrm trans, MeDataSource dataSource) throws SQLException;
	boolean checkExist(Transaction tran, MeDataSource dataSource) throws SQLException;
	boolean checkPostJournal(Transaction tran, MeDataSource dataSource) throws SQLException;
	boolean checkDrCr(Transaction tran, MeDataSource dataSource) throws SQLException;
	String checkReference(Transaction tran, MeDataSource dataSource) throws SQLException;
}
