package balancika.ame.service;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.tansaction.Sale;

public interface SaleService {
	Sale getSale(Sale sale, MeDataSource dataSource);
}
