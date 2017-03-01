package balancika.ame.service;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.tansaction.DebitNote;

public interface DebitNoteService {
	DebitNote getDebitNote(DebitNote debitNote, MeDataSource dataSource);
}
