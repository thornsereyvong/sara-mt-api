package balancika.ame.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import balancika.ame.entities.Authorization;
import balancika.ame.entities.MeDataSource;

public interface AuthorizationService {
	List<Authorization> listAuthorization(MeDataSource dataSource) throws SQLException;
	Map<String, Object> createAuthorization(MeDataSource dataSource, Authorization authori) throws SQLException;
	Map<String, Object> deleteAuthorization(MeDataSource dataSource, String authId) throws SQLException;
	Map<String, Object> getByIdAuthorization(MeDataSource dataSource, String authId) throws SQLException;
	Map<String, Object> updateAuthorization(MeDataSource dataSource,  Authorization authori) throws SQLException;
}
