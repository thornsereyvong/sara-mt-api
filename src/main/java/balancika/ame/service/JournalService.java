package balancika.ame.service;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.tansaction.Journal;

public interface JournalService {
	Journal getJournal(Journal jn, MeDataSource dataSource);
}
