package balancika.ame.entities.tansaction;

import java.sql.Date;
import java.util.List;

import balancika.ame.entities.maintain.Customer;
import balancika.ame.entities.maintain.Employee;
import balancika.ame.entities.setting.Class;
import balancika.ame.entities.setting.Location;
import balancika.ame.entities.setting.PriceCode;

public class SaleReturn {
	
	private String saleReturnId;
	private Date saleReturnDate;	
	private String saleId;
	private String saleType;
	private String saleReference;
	private Date saleDate; 
	private int periodM;
	private int periodY;
	private String postStatus; 
	private String pmtStatus; 
	private int jId;
	private String pmtSchId;  
	private double totalAmount;
	private double disInvDol;
	private double disInvPer;
	private double totalDis; 
	private double totalSTax;
	private double totalVTax;
	private double netTotalAmt;
	private double pmtToDate;
	private double cash;
	private double cashCard;	
	private Date pmtDisDate; 
	private String remark; 
	private String message; 	
	private Date dueDate; 	
	private String shipTo;
	
	private Class classCode;
	private Location location;
	private PriceCode priceCode;
	private Customer customer;
	private Employee employee;
	
	List<SaleReturnDetail> saleReturnDetail;

	public String getSaleReturnId() {
		return saleReturnId;
	}

	public void setSaleReturnId(String saleReturnId) {
		this.saleReturnId = saleReturnId;
	}

	public Date getSaleReturnDate() {
		return saleReturnDate;
	}

	public void setSaleReturnDate(Date saleReturnDate) {
		this.saleReturnDate = saleReturnDate;
	}

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public String getSaleReference() {
		return saleReference;
	}

	public void setSaleReference(String saleReference) {
		this.saleReference = saleReference;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public int getPeriodM() {
		return periodM;
	}

	public void setPeriodM(int periodM) {
		this.periodM = periodM;
	}

	public int getPeriodY() {
		return periodY;
	}

	public void setPeriodY(int periodY) {
		this.periodY = periodY;
	}

	public String getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}

	public String getPmtStatus() {
		return pmtStatus;
	}

	public void setPmtStatus(String pmtStatus) {
		this.pmtStatus = pmtStatus;
	}

	public int getjId() {
		return jId;
	}

	public void setjId(int jId) {
		this.jId = jId;
	}

	public String getPmtSchId() {
		return pmtSchId;
	}

	public void setPmtSchId(String pmtSchId) {
		this.pmtSchId = pmtSchId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getDisInvDol() {
		return disInvDol;
	}

	public void setDisInvDol(double disInvDol) {
		this.disInvDol = disInvDol;
	}

	public double getDisInvPer() {
		return disInvPer;
	}

	public void setDisInvPer(double disInvPer) {
		this.disInvPer = disInvPer;
	}

	public double getTotalDis() {
		return totalDis;
	}

	public void setTotalDis(double totalDis) {
		this.totalDis = totalDis;
	}

	public double getTotalSTax() {
		return totalSTax;
	}

	public void setTotalSTax(double totalSTax) {
		this.totalSTax = totalSTax;
	}

	public double getTotalVTax() {
		return totalVTax;
	}

	public void setTotalVTax(double totalVTax) {
		this.totalVTax = totalVTax;
	}

	public double getNetTotalAmt() {
		return netTotalAmt;
	}

	public void setNetTotalAmt(double netTotalAmt) {
		this.netTotalAmt = netTotalAmt;
	}

	public double getPmtToDate() {
		return pmtToDate;
	}

	public void setPmtToDate(double pmtToDate) {
		this.pmtToDate = pmtToDate;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getCashCard() {
		return cashCard;
	}

	public void setCashCard(double cashCard) {
		this.cashCard = cashCard;
	}

	public Date getPmtDisDate() {
		return pmtDisDate;
	}

	public void setPmtDisDate(Date pmtDisDate) {
		this.pmtDisDate = pmtDisDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getShipTo() {
		return shipTo;
	}

	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}

	public Class getClassCode() {
		return classCode;
	}

	public void setClassCode(Class classCode) {
		this.classCode = classCode;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public PriceCode getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(PriceCode priceCode) {
		this.priceCode = priceCode;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<SaleReturnDetail> getSaleReturnDetail() {
		return saleReturnDetail;
	}

	public void setSaleReturnDetail(List<SaleReturnDetail> saleReturnDetail) {
		this.saleReturnDetail = saleReturnDetail;
	}
	
	
	
}
