package balancika.ame.entities.setting;

import java.util.List;

public class StationGroup {
	
	private String stationGroupId;
	private String stationGroupName;
	private String description;
	private List<Station> stations;
	
	public String getStationGroupId() {
		return stationGroupId;
	}
	public void setStationGroupId(String stationGroupId) {
		this.stationGroupId = stationGroupId;
	}
	public String getStationGroupName() {
		return stationGroupName;
	}
	public void setStationGroupName(String stationGroupName) {
		this.stationGroupName = stationGroupName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Station> getStations() {
		return stations;
	}
	public void setStations(List<Station> stations) {
		this.stations = stations;
	}	
}
