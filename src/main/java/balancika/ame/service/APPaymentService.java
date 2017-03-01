package balancika.ame.service;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.tansaction.APPayment;

public interface APPaymentService {
	APPayment getAPPayment(APPayment apPayment, MeDataSource dataSource);
}
