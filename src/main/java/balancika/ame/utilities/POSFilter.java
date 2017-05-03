package balancika.ame.utilities;

import java.util.Map;
import balancika.ame.entities.MeDataSource;

public class POSFilter {
	private MeDataSource dataSource;
	private Map<String, String> filter;
	
	public MeDataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(MeDataSource dataSource) {
		this.dataSource = dataSource;
	}
	public Map<String, String> getFilter() {
		return filter;
	}
	public void setFilter(Map<String, String> filter) {
		this.filter = filter;
	}	
}
