package balancika.ame.service.pos;

import java.util.List;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.setting.StationGroup;

public interface StationGroupService {
	
	List<StationGroup> listStationGroup(MeDataSource dataSource);
	
	
}
