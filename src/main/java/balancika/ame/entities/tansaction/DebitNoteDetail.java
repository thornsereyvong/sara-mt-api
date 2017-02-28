package balancika.ame.entities.tansaction;


import balancika.ame.entities.maintain.Item;
import balancika.ame.entities.setting.Class;
import balancika.ame.entities.setting.Location;
import balancika.ame.entities.setting.Uom;

public class DebitNoteDetail {
	private String entryId;
	private int lineNo;
	private String jId;
	private Item item;
	private Location location;
	private Uom uom;
	private Class classCode;
	private double qty;
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
	public String getjId() {
		return jId;
	}
	public void setjId(String jId) {
		this.jId = jId;
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
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
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
