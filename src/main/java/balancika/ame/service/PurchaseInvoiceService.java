package balancika.ame.service;


import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.tansaction.PurchaseInvoice;


public interface PurchaseInvoiceService {
	
	PurchaseInvoice getPurchaseInvoice(PurchaseInvoice purchase,MeDataSource dataSource);
	
	
}
