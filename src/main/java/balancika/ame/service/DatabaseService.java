package balancika.ame.service;

import java.util.List;
import java.util.Map;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.SDCompany;

public interface DatabaseService {
	List<SDCompany> listAllSystemDatabase(MeDataSource dataSource);
	List<Map<String, Object>> getDefaultConfig(MeDataSource dataSource);
}
