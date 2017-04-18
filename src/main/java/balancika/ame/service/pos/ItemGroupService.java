package balancika.ame.service.pos;

import java.util.Map;

import balancika.ame.entities.MeDataSource;

public interface ItemGroupService {
	Map<String, Object> listItemGroupGroup(String filter,MeDataSource dataSource);
}
