package balancika.ame.service;

import java.sql.SQLException;
import java.util.List;

import balancika.ame.entities.Authorization;
import balancika.ame.entities.MeDataSource;

public interface AuthorizationService {
	List<Authorization> listAuthorization(MeDataSource dataSource) throws SQLException;
}
