package balancika.ame.entities.tansaction;

import java.sql.Date;
import java.util.List;

import balancika.ame.entities.maintain.Vendor;
import balancika.ame.entities.setting.Class;

public class PurchaseInvoice {
	private String purchaseId;
	private String reference;
	private  Date purchaseDate;
	private  Date dueDate;
	private  String purchaseType;
	private  String postStatus;
	private  Vendor vendor;
	private  String remark;
	private  Class classCode;
	private double disInvDol;
	private double disInvPer;
	private double totalDis;
	private double totalST;
	private double totalVAT;
	private double netTotalAmt;
	private double totalTax;
	
	List<PurchaseInvoiceDetail> purchaseDetail;
	
	public String getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getTransactionType() {
		return purchaseType;
	}
	public void setTransactionType(String transactionType) {
		this.purchaseType = transactionType;
	}	
	public String getPostStatus() {
		return postStatus;
	}
	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Class getClassCode() {
		return classCode;
	}
	public void setClassCode(Class classCode) {
		this.classCode = classCode;
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
	public double getTotalST() {
		return totalST;
	}
	public void setTotalST(double totalST) {
		this.totalST = totalST;
	}
	public double getTotalVAT() {
		return totalVAT;
	}
	public void setTotalVAT(double totalVAT) {
		this.totalVAT = totalVAT;
	}
	public double getNetTotalAmt() {
		return netTotalAmt;
	}
	public void setNetTotalAmt(double netTotalAmt) {
		this.netTotalAmt = netTotalAmt;
	}
	public double getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}
	public List<PurchaseInvoiceDetail> getPurchaseDetail() {
		return purchaseDetail;
	}
	public void setPurchaseDetail(List<PurchaseInvoiceDetail> purchaseDetail) {
		this.purchaseDetail = purchaseDetail;
	}
	
	
	
}
