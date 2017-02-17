package balancika.ame.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import balancika.ame.entities.AuthorizationGroup;
import balancika.ame.entities.MeDataSource;

@Repository
public interface AuthorizationGroupService {
	List<AuthorizationGroup> listAuthorizationGroup(MeDataSource dataSource) throws SQLException;
}
