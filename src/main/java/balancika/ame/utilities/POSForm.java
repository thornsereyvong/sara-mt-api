package balancika.ame.utilities;

import balancika.ame.entities.MeDataSource;

import java.util.List;
import java.util.Map;
public class POSForm {
	private MeDataSource dataSource;
	private List<Map<String,Object>> records;
	public MeDataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(MeDataSource dataSource) {
		this.dataSource = dataSource;
	}
	public List<Map<String, Object>> getRecords() {
		return records;
	}
	public void setRecords(List<Map<String, Object>> records) {
		this.records = records;
	}
}
