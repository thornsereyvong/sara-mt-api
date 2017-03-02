package balancika.ame.entities.tansaction;

import java.sql.Date;
import java.util.List;

import balancika.ame.entities.maintain.Customer;
import balancika.ame.entities.setting.Account;
import balancika.ame.entities.setting.Class;

public class ARReceipt {
	private String rcpId;
	private String rcpType;
	private Date rcpDate;
	private String reference;
	private String postStatus;
	private String remark;
	private Customer customer;
	private Account account;
	private Class classCode;
	private double totalDiscount;
	private double totalPmtAmount;
	private double totalCash;
	private String jId;
	private String appliedStatus;
	private String appliedAmt;
	private List<ARReceiptDetail> receiptDetail;
	public String getRcpId() {
		return rcpId;
	}
	public void setRcpId(String rcpId) {
		this.rcpId = rcpId;
	}
	public String getRcpType() {
		return rcpType;
	}
	public void setRcpType(String rcpType) {
		this.rcpType = rcpType;
	}
	public Date getRcpDate() {
		return rcpDate;
	}
	public void setRcpDate(Date rcpDate) {
		this.rcpDate = rcpDate;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getPostStatus() {
		return postStatus;
	}
	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Class getClassCode() {
		return classCode;
	}
	public void setClassCode(Class classCode) {
		this.classCode = classCode;
	}
	public double getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public double getTotalPmtAmount() {
		return totalPmtAmount;
	}
	public void setTotalPmtAmount(double totalPmtAmount) {
		this.totalPmtAmount = totalPmtAmount;
	}
	public double getTotalCash() {
		return totalCash;
	}
	public void setTotalCash(double totalCash) {
		this.totalCash = totalCash;
	}
	public String getjId() {
		return jId;
	}
	public void setjId(String jId) {
		this.jId = jId;
	}
	public String getAppliedStatus() {
		return appliedStatus;
	}
	public void setAppliedStatus(String appliedStatus) {
		this.appliedStatus = appliedStatus;
	}
	public String getAppliedAmt() {
		return appliedAmt;
	}
	public void setAppliedAmt(String appliedAmt) {
		this.appliedAmt = appliedAmt;
	}
	public List<ARReceiptDetail> getReceiptDetail() {
		return receiptDetail;
	}
	public void setReceiptDetail(List<ARReceiptDetail> receiptDetail) {
		this.receiptDetail = receiptDetail;
	}
	
}
