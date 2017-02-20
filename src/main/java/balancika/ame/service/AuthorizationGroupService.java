package balancika.ame.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import balancika.ame.entities.AuthorizationGroup;
import balancika.ame.entities.MeDataSource;

@Repository
public interface AuthorizationGroupService {
	List<AuthorizationGroup> listAuthorizationGroup(MeDataSource dataSource) throws SQLException;
	Map<String, Object> createAuthorizationGroup(AuthorizationGroup authoriGroup, MeDataSource dataSoruce) throws SQLException;
	Map<String, Object> deleteAuthorizationGroup(MeDataSource dataSource,String ID) throws SQLException;
	Map<String, Object> getAuthorizationGroup(String ID, MeDataSource dataSource) throws SQLException;
}
