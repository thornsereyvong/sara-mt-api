package balancika.ame.service.pos;

import java.util.List;
import java.util.Map;

import balancika.ame.entities.MeDataSource;

public interface ItemService {
	List<Map<String, Object>> listItemDetail(String filter,MeDataSource dataSource);
}
