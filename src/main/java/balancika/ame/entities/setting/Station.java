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
	
	String getStationId() {
		return stationId;
	}
	void setStationId(String stationId) {
		this.stationId = stationId;
	}
	String getStationName() {
		return stationName;
	}
	void setStationName(String stationName) {
		this.stationName = stationName;
	}
	String getStationType() {
		return stationType;
	}
	void setStationType(String stationType) {
		this.stationType = stationType;
	}
	String getStationGroupId() {
		return stationGroupId;
	}
	void setStationGroupId(String stationGroupId) {
		this.stationGroupId = stationGroupId;
	}
	String getDescription() {
		return description;
	}
	void setDescription(String description) {
		this.description = description;
	}
	int getGuestNum() {
		return guestNum;
	}
	void setGuestNum(int guestNum) {
		this.guestNum = guestNum;
	}
	String getStatus() {
		return status;
	}
	void setStatus(String status) {
		this.status = status;
	}
	Date getStartTime() {
		return startTime;
	}
	void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	String getCustId() {
		return custId;
	}
	void setCustId(String custId) {
		this.custId = custId;
	}
	String getCustName() {
		return custName;
	}
	void setCustName(String custName) {
		this.custName = custName;
	}
	PriceCode getPriceCode() {
		return priceCode;
	}
	void setPriceCode(PriceCode priceCode) {
		this.priceCode = priceCode;
	}
	int getBillCount() {
		return billCount;
	}
	void setBillCount(int billCount) {
		this.billCount = billCount;
	}
}
