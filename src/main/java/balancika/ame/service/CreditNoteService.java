package balancika.ame.service;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.tansaction.CreditNote;

public interface CreditNoteService {
	CreditNote getCreditNote(CreditNote cdn, MeDataSource dataSource);
}
