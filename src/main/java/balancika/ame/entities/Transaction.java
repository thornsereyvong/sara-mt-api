package balancika.ame.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {
	
	String transId;
	String transName;
	String transDate;
	String transReference;
	String transRemark;
	String transStatus;
	Double transAmt;
	
	
	// search field
	
	String fromDate;
	String toDate;
	String filterType;
	String search;
	String transType;
	int isVoid =0;
	int searchClick = 0;
	
	
	
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getTransName() {
		return transName;
	}
	public void setTransName(String transName) {
		this.transName = transName;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getTransReference() {
		return transReference;
	}
	public void setTransReference(String transReference) {
		this.transReference = transReference;
	}
	public String getTransRemark() {
		return transRemark;
	}
	public void setTransRemark(String transRemark) {
		this.transRemark = transRemark;
	}
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	public Double getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(Double transAmt) {
		this.transAmt = transAmt;
	}
	
	
	
	
	
	@JsonIgnore
    @JsonProperty(value = "fromDate")
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	@JsonIgnore
    @JsonProperty(value = "toDate")
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	@JsonIgnore
    @JsonProperty(value = "filterType")
	public String getFilterType() {
		return filterType;
	}
	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}
	@JsonIgnore
    @JsonProperty(value = "search")
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	@JsonIgnore
    @JsonProperty(value = "isVoid")
	public int getIsVoid() {
		return isVoid;
	}
	public void setIsVoid(int isVoid) {
		this.isVoid = isVoid;
	}
	@JsonIgnore
    @JsonProperty(value = "searchClick")
	public int getSearchClick() {
		return searchClick;
	}
	public void setSearchClick(int searchClick) {
		this.searchClick = searchClick;
	}
	
	@JsonIgnore
    @JsonProperty(value = "transType")
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	
}
