package balancika.ame.service;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.tansaction.ARReceipt;

public interface ARReceiptService {
	ARReceipt getARReceipt(ARReceipt rcp, MeDataSource dataSource);
}
