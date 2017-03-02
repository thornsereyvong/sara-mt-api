package balancika.ame.entities.tansaction;

import balancika.ame.entities.maintain.Item;
import balancika.ame.entities.setting.Class;
import balancika.ame.entities.setting.Location;
import balancika.ame.entities.setting.Uom;

public class ICAdjustmentDetail {
	
	private String adjId;
	private int lineNo;
	private Item item;
	private Location location;
	private int batchNo;
	private double adjQty;
	private Uom uom;
	private double adjCost;
	private double amount;
	private String postStatus;
	private String jId;
	private Class classCode;
	public String getAdjId() {
		return adjId;
	}
	public void setAdjId(String adjId) {
		this.adjId = adjId;
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
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public int getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}
	public double getAdjQty() {
		return adjQty;
	}
	public void setAdjQty(double adjQty) {
		this.adjQty = adjQty;
	}
	public Uom getUom() {
		return uom;
	}
	public void setUom(Uom uom) {
		this.uom = uom;
	}
	public double getAdjCost() {
		return adjCost;
	}
	public void setAdjCost(double adjCost) {
		this.adjCost = adjCost;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
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
	
}
