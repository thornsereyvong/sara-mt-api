package balancika.ame.service;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.tansaction.PurchaseReturn;

public interface PurchaseReturnService {
	PurchaseReturn getPurchaseInvoice(PurchaseReturn purReturn , MeDataSource dataSource);
}
