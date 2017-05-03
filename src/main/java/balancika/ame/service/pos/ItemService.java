package balancika.ame.service.pos;

import java.util.List;
import java.util.Map;

import balancika.ame.entities.MeDataSource;
import balancika.ame.utilities.POSFilter;

public interface ItemService {
	List<Map<String, Object>> listItemDetail(String filter,MeDataSource dataSource);
	List<Map<String, Object>> ListItemDetailWithMap(POSFilter pos);
}
