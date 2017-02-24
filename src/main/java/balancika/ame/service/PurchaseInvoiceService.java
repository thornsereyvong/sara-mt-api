package balancika.ame.service;

import org.springframework.stereotype.Repository;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.tansaction.PurchaseInvoice;

@Repository
public interface PurchaseInvoiceService {
	
	PurchaseInvoice getPurchaseInvoice(PurchaseInvoice purchase,MeDataSource dataSource);
	
	
}
