package balancika.ame.entities.tansaction;

import balancika.ame.entities.setting.Class;

public class ARReceiptDetail {
	
	private String rcpId;
	private int lineNo;
	private Sale sale;
	private double discount;
	private String expAccId;
	private String transDescription;
	private double pmtAmount;
	private String postStatus;
	private String jId;
	private Class classCode;
	private double appliedAmt;
	private double amountDue;
	private double netAmountDue;
	public String getRcpId() {
		return rcpId;
	}
	public void setRcpId(String rcpId) {
		this.rcpId = rcpId;
	}
	public int getLineNo() {
		return lineNo;
	}
	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}
	public Sale getSale() {
		return sale;
	}
	public void setSale(Sale sale) {
		this.sale = sale;
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
	public double getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}
	public double getNetAmountDue() {
		return netAmountDue;
	}
	public void setNetAmountDue(double netAmountDue) {
		this.netAmountDue = netAmountDue;
	}
	
	
	
}
