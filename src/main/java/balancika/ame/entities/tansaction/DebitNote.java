package balancika.ame.entities.tansaction;

import java.sql.Date;
import java.util.List;

import balancika.ame.entities.maintain.Vendor;
import balancika.ame.entities.setting.Class;

public class DebitNote {
	private String entryId;
	private String reference;
	private  Date debitNoteDate;
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
	private double totalAppPay;
	private double netTotalInvoice;
	private List<DebitNoteDetail> debitNoteDetail;
	
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
	public Date getDebitNoteDate() {
		return debitNoteDate;
	}
	public void setDebitNoteDate(Date debitNoteDate) {
		this.debitNoteDate = debitNoteDate;
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
	public double getTotalAppPay() {
		return totalAppPay;
	}
	public void setTotalAppPay(double totalAppPay) {
		this.totalAppPay = totalAppPay;
	}
	public double getNetTotalInvoice() {
		return netTotalInvoice;
	}
	public void setNetTotalInvoice(double netTotalInvoice) {
		this.netTotalInvoice = netTotalInvoice;
	}
	public List<DebitNoteDetail> getDebitNoteDetail() {
		return debitNoteDetail;
	}
	public void setDebitNoteDetail(List<DebitNoteDetail> debitNoteDetail) {
		this.debitNoteDetail = debitNoteDetail;
	}
	
	
}
