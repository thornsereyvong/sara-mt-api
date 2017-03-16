package balancika.ame.service.report;

import java.util.List;
import java.util.Map;

import balancika.ame.entities.MeDataSource;

public interface AccountReceivableService {
	
	List<Map<String, Object>> invoiceRegisterSummary(Map<String,String> criteria, MeDataSource dataSource);
	Map<String, Object> startupInvoiceRegister(MeDataSource dataSource);
	
}
