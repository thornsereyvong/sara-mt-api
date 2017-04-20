package balancika.ame.service.pos;

import java.util.List;
import java.util.Map;
import balancika.ame.entities.MeDataSource;

public interface StationGroupService {
	
	Map<String, Object> listStationGroup(MeDataSource dataSource);
	List<Map<String, Object>> listStationByStationGroup(String stationId,MeDataSource dataSource);
	
}
