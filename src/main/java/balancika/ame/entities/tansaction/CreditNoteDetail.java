package balancika.ame.entities.tansaction;

import balancika.ame.entities.maintain.Item;
import balancika.ame.entities.setting.Class;
import balancika.ame.entities.setting.Location;
import balancika.ame.entities.setting.Uom;

public class CreditNoteDetail {
	private String entryId;
	private int lineNo;
	private Item item;
	private Uom uom;
	private Location location;
	private Class classCode;
	private double qty;
	private double unitPrice;
	private double totalAmt;
	private double COGSUnit;
	private double COGSAC1;
	private double COGSAC2;
	private double COGSAC3;
	private double COGSAC4;
	private double COGSAC5;
	private double COGSTotal;
	private String postStatus;
	private int jId;
	private double netTotalAmt;
	private double disDol;
	private double disPer;
	private double STaxDol;
	private double STaxPer;
	private double VTaxDol;
	private double VTaxPer;
	private String itemStatus;
	private double reportPrice;
	private int accId;
	private String des;
	private double factor;
	private double disInv;
	public String getEntryId() {
		return entryId;
	}
	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}
	public int getLineNo() {
		return lineNo;
	}
	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Uom getUom() {
		return uom;
	}
	public void setUom(Uom uom) {
		this.uom = uom;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Class getClassCode() {
		return classCode;
	}
	public void setClassCode(Class classCode) {
		this.classCode = classCode;
	}
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
	}
	public double getCOGSUnit() {
		return COGSUnit;
	}
	public void setCOGSUnit(double cOGSUnit) {
		COGSUnit = cOGSUnit;
	}
	public double getCOGSAC1() {
		return COGSAC1;
	}
	public void setCOGSAC1(double cOGSAC1) {
		COGSAC1 = cOGSAC1;
	}
	public double getCOGSAC2() {
		return COGSAC2;
	}
	public void setCOGSAC2(double cOGSAC2) {
		COGSAC2 = cOGSAC2;
	}
	public double getCOGSAC3() {
		return COGSAC3;
	}
	public void setCOGSAC3(double cOGSAC3) {
		COGSAC3 = cOGSAC3;
	}
	public double getCOGSAC4() {
		return COGSAC4;
	}
	public void setCOGSAC4(double cOGSAC4) {
		COGSAC4 = cOGSAC4;
	}
	public double getCOGSAC5() {
		return COGSAC5;
	}
	public void setCOGSAC5(double cOGSAC5) {
		COGSAC5 = cOGSAC5;
	}
	public double getCOGSTotal() {
		return COGSTotal;
	}
	public void setCOGSTotal(double cOGSTotal) {
		COGSTotal = cOGSTotal;
	}
	public String getPostStatus() {
		return postStatus;
	}
	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}
	public int getjId() {
		return jId;
	}
	public void setjId(int jId) {
		this.jId = jId;
	}
	public double getNetTotalAmt() {
		return netTotalAmt;
	}
	public void setNetTotalAmt(double netTotalAmt) {
		this.netTotalAmt = netTotalAmt;
	}
	public double getDisDol() {
		return disDol;
	}
	public void setDisDol(double disDol) {
		this.disDol = disDol;
	}
	public double getDisPer() {
		return disPer;
	}
	public void setDisPer(double disPer) {
		this.disPer = disPer;
	}
	public double getSTaxDol() {
		return STaxDol;
	}
	public void setSTaxDol(double sTaxDol) {
		STaxDol = sTaxDol;
	}
	public double getSTaxPer() {
		return STaxPer;
	}
	public void setSTaxPer(double sTaxPer) {
		STaxPer = sTaxPer;
	}
	public double getVTaxDol() {
		return VTaxDol;
	}
	public void setVTaxDol(double vTaxDol) {
		VTaxDol = vTaxDol;
	}
	public double getVTaxPer() {
		return VTaxPer;
	}
	public void setVTaxPer(double vTaxPer) {
		VTaxPer = vTaxPer;
	}
	public String getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	public double getReportPrice() {
		return reportPrice;
	}
	public void setReportPrice(double reportPrice) {
		this.reportPrice = reportPrice;
	}
	public int getAccId() {
		return accId;
	}
	public void setAccId(int accId) {
		this.accId = accId;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public double getFactor() {
		return factor;
	}
	public void setFactor(double factor) {
		this.factor = factor;
	}
	public double getDisInv() {
		return disInv;
	}
	public void setDisInv(double disInv) {
		this.disInv = disInv;
	}
	
	
	
}
