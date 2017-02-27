package balancika.ame.entities.tansaction;

import balancika.ame.entities.maintain.Item;
import balancika.ame.entities.setting.Class;
import balancika.ame.entities.setting.Location;
import balancika.ame.entities.setting.Uom;

public class PurchaseReturnDetail {
	private String purchaseReturnId;
	private int lineNo;
	private String transType;
	private String transDesc;
	private int batchNo;
	private int accId;
	private Item item;
	private Location location;
	private Uom uom;
	private Class classCode;
	private double retQty;
	private double unitCost;
	private double totalCost;
	private double factor;
	private double disDol;
	private double disPer;
	private double staxDol;
	private double staxPer;
	private double vatDol;
	private double vatPer;
	private double netTotalAmt;
	public String getPurchaseReturnId() {
		return purchaseReturnId;
	}
	public void setPurchaseReturnId(String purchaseReturnId) {
		this.purchaseReturnId = purchaseReturnId;
	}
	public int getLineNo() {
		return lineNo;
	}
	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getTransDesc() {
		return transDesc;
	}
	public void setTransDesc(String transDesc) {
		this.transDesc = transDesc;
	}
	public int getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}
	public int getAccId() {
		return accId;
	}
	public void setAccId(int accId) {
		this.accId = accId;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Uom getUom() {
		return uom;
	}
	public void setUom(Uom uom) {
		this.uom = uom;
	}
	public Class getClassCode() {
		return classCode;
	}
	public void setClassCode(Class classCode) {
		this.classCode = classCode;
	}
	public double getRetQty() {
		return retQty;
	}
	public void setRetQty(double retQty) {
		this.retQty = retQty;
	}
	public double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public double getFactor() {
		return factor;
	}
	public void setFactor(double factor) {
		this.factor = factor;
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
	public double getStaxDol() {
		return staxDol;
	}
	public void setStaxDol(double staxDol) {
		this.staxDol = staxDol;
	}
	public double getStaxPer() {
		return staxPer;
	}
	public void setStaxPer(double staxPer) {
		this.staxPer = staxPer;
	}
	public double getVatDol() {
		return vatDol;
	}
	public void setVatDol(double vatDol) {
		this.vatDol = vatDol;
	}
	public double getVatPer() {
		return vatPer;
	}
	public void setVatPer(double vatPer) {
		this.vatPer = vatPer;
	}
	public double getNetTotalAmt() {
		return netTotalAmt;
	}
	public void setNetTotalAmt(double netTotalAmt) {
		this.netTotalAmt = netTotalAmt;
	}
	
	
	
}
