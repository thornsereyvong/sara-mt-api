package balancika.ame.service;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.tansaction.CashAdvanceClearance;

public interface CashAdvanceClearanceService {
	CashAdvanceClearance getCashAdvanceClearance(CashAdvanceClearance cl, MeDataSource dataSource);
}
