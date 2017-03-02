package balancika.ame.service;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.tansaction.SaleReturn;

public interface SaleReturnService {
	SaleReturn getSaleReturn(SaleReturn sr, MeDataSource dataSource);
}
