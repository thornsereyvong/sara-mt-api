package balancika.ame.entities.tansaction;

import java.sql.Date;
import java.util.List;

import balancika.ame.entities.maintain.Customer;
import balancika.ame.entities.maintain.Employee;
import balancika.ame.entities.setting.Class;
import balancika.ame.entities.setting.Location;
import balancika.ame.entities.setting.PriceCode;

public class CreditNote {
	private String entryId;
	private String reference;
	private Date CreditNoteDate; 
	private int periodM;
	private int periodY;
	private String postStatus; 
	private String pmtStatus; 
	private int jId;
	private double totalAmount;
	private double disInvDol;
	private double disInvPer;
	private double totalDis; 
	private double totalSTax;
	private double totalVTax;
	private double netTotalAmt;
	private double pmtToDate;
	private Date pmtDisDate; 
	private String remark; 		
	private String shipTo;
	private Class classCode;
	private Location location;
	private PriceCode priceCode;
	private Customer customer;
	private Employee employee;
	private List<CreditNoteDetail> creditNoteDetail;
	public String getEntryId() {
		return entryId;
	}
	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Date getCreditNoteDate() {
		return CreditNoteDate;
	}
	public void setCreditNoteDate(Date creditNoteDate) {
		CreditNoteDate = creditNoteDate;
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
	public List<CreditNoteDetail> getCreditNoteDetail() {
		return creditNoteDetail;
	}
	public void setCreditNoteDetail(List<CreditNoteDetail> creditNoteDetail) {
		this.creditNoteDetail = creditNoteDetail;
	}
	
}
