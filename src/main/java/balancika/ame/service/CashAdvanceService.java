package balancika.ame.service;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.tansaction.CashAdvance;

public interface CashAdvanceService {
	CashAdvance getCashAdvance(CashAdvance ca, MeDataSource dataSource);
}
