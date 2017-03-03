package balancika.ame.service;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.tansaction.CashTransfer;

public interface CashTransferService {
	CashTransfer getCashTransfer(CashTransfer ctf, MeDataSource dataSource);
}
