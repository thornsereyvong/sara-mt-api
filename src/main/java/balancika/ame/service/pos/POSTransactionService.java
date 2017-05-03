package balancika.ame.service.pos;

import java.util.List;
import java.util.Map;

import balancika.ame.utilities.POSFilter;
import balancika.ame.utilities.POSForm;

public interface POSTransactionService {
	List<Map<String, Object>> listSaleOrder(POSFilter pos);
	boolean addSaleOrder(POSForm frm);
	boolean deleteSaleOrderByStation(POSForm frm);
	boolean updateSaleOrderByStation(POSForm frm);
}
