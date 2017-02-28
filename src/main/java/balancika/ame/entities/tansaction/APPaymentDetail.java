package balancika.ame.entities.tansaction;

import balancika.ame.entities.setting.Class;

public class APPaymentDetail {
	
	private String pmtId;
	private int lineNo;
	private PurchaseInvoice purchase;
	private double discount;
	private String expAccId;
	private String transDescription;
	private double pmtAmount;
	private String postStatus;
	private String jId;
	private Class classCode;
	private double appliedAmt;
	
	public String getPmtId() {
		return pmtId;
	}
	public void setPmtId(String pmtId) {
		this.pmtId = pmtId;
	}
	public int getLineNo() {
		return lineNo;
	}
	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}	
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getExpAccId() {
		return expAccId;
	}
	public void setExpAccId(String expAccId) {
		this.expAccId = expAccId;
	}
	public String getTransDescription() {
		return transDescription;
	}
	public void setTransDescription(String transDescription) {
		this.transDescription = transDescription;
	}
	public double getPmtAmount() {
		return pmtAmount;
	}
	public void setPmtAmount(double pmtAmount) {
		this.pmtAmount = pmtAmount;
	}
	public String getPostStatus() {
		return postStatus;
	}
	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}
	public String getjId() {
		return jId;
	}
	public void setjId(String jId) {
		this.jId = jId;
	}
	public Class getClassCode() {
		return classCode;
	}
	public void setClassCode(Class classCode) {
		this.classCode = classCode;
	}
	public double getAppliedAmt() {
		return appliedAmt;
	}
	public void setAppliedAmt(double appliedAmt) {
		this.appliedAmt = appliedAmt;
	}
	public PurchaseInvoice getPurchase() {
		return purchase;
	}
	public void setPurchase(PurchaseInvoice purchase) {
		this.purchase = purchase;
	}
	
	
	
}
