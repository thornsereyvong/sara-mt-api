package balancika.ame.service;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.tansaction.ICTransfer;

public interface ICTransferService {
	ICTransfer getICTransfer(ICTransfer trf, MeDataSource dataSource);
}
