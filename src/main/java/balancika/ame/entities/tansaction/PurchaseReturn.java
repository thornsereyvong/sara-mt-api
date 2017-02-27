package balancika.ame.entities.tansaction;

import java.sql.Date;
import java.util.List;

import balancika.ame.entities.maintain.Vendor;
import balancika.ame.entities.setting.Class;

public class PurchaseReturn {
	private String purchaseReturnId;
	private String reference;
	private  Date purchaseReturnDate;
	private  String postStatus;
	private  Vendor vendor;
	private  String remark;
	private  Class classCode;
	private double disInvDol;
	private double disInvPer;
	private double totalAmt;
	private double totalDis;
	private double totalST;
	private double totalVAT;
	private double netTotalAmt;
	private double totalTax;
	private List<PurchaseReturnDetail> purchaseReturnDetail;
	public String getPurchaseReturnId() {
		return purchaseReturnId;
	}
	public void setPurchaseReturnId(String purchaseReturnId) {
		this.purchaseReturnId = purchaseReturnId;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Date getPurchaseReturnDate() {
		return purchaseReturnDate;
	}
	public void setPurchaseReturnDate(Date purchaseReturnDate) {
		this.purchaseReturnDate = purchaseReturnDate;
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
	public double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
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
	public List<PurchaseReturnDetail> getPurchaseReturnDetail() {
		return purchaseReturnDetail;
	}
	public void setPurchaseReturnDetail(List<PurchaseReturnDetail> purchaseReturnDetail) {
		this.purchaseReturnDetail = purchaseReturnDetail;
	}
	
	
}
