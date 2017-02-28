package balancika.ame.entities.tansaction;

import java.sql.Date;
import java.util.List;

import balancika.ame.entities.maintain.Vendor;
import balancika.ame.entities.setting.Account;
import balancika.ame.entities.setting.Class;

public class APPayment {
	
	private String pmtId;
	private String pmtType;
	private Date pmtDate;
	private String reference;
	private String postStatus;
	private String remark;
	private Vendor vendor;
	private Account account;
	private Class classCode;
	private double totalDiscount;
	private double totalPmtAmount;
	private double totalCash;
	private String jId;
	private String appliedStatus;
	private String appliedAmt;
	private List<APPaymentDetail> apPaymentDetail;

	public String getPmtId() {
		return pmtId;
	}

	public void setPmtId(String pmtId) {
		this.pmtId = pmtId;
	}

	public String getPmtType() {
		return pmtType;
	}

	public void setPmtType(String pmtType) {
		this.pmtType = pmtType;
	}

	public Date getPmtDate() {
		return pmtDate;
	}

	public void setPmtDate(Date pmtDate) {
		this.pmtDate = pmtDate;
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

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
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

	public List<APPaymentDetail> getApPaymentDetail() {
		return apPaymentDetail;
	}

	public void setApPaymentDetail(List<APPaymentDetail> apPaymentDetail) {
		this.apPaymentDetail = apPaymentDetail;
	}
	
}	
