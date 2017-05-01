package balancika.ame.entities.setting;

import java.sql.Date;

public class Station {
	
	private String stationId;
	private String stationName;
	private String stationType;
	private String stationGroupId;
	private String description;
	private int guestNum;
	private String status;
	private Date startTime;
	private String custId;
	private String custName;
	private PriceCode priceCode;
	private int billCount;
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getStationType() {
		return stationType;
	}
	public void setStationType(String stationType) {
		this.stationType = stationType;
	}
	public String getStationGroupId() {
		return stationGroupId;
	}
	public void setStationGroupId(String stationGroupId) {
		this.stationGroupId = stationGroupId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getGuestNum() {
		return guestNum;
	}
	public void setGuestNum(int guestNum) {
		this.guestNum = guestNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public PriceCode getPriceCode() {
		return priceCode;
	}
	public void setPriceCode(PriceCode priceCode) {
		this.priceCode = priceCode;
	}
	public int getBillCount() {
		return billCount;
	}
	public void setBillCount(int billCount) {
		this.billCount = billCount;
	}
	
	
}
