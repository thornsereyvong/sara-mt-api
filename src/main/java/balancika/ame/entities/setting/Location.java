package balancika.ame.entities.setting;

public class Location {
	private String systemCodeId;
	private String locationId;
	private String locationName;
	private String address;
	private String remark;
	
	public String getSystemCodeId() {
		return systemCodeId;
	}
	public void setSystemCodeId(String systemCodeId) {
		this.systemCodeId = systemCodeId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Location(String locationId, String locationName) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
	}
	
	
	
}
