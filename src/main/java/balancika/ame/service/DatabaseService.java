package balancika.ame.service;

import java.util.List;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.SDCompany;

public interface DatabaseService {
	List<SDCompany> listAllSystemDatabase(MeDataSource dataSource);
}
