package balancika.ame.service;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.tansaction.ICAdjustment;

public interface ICAdjustmentService {
	ICAdjustment getICAdjustment(ICAdjustment adj, MeDataSource dataSource);
}
