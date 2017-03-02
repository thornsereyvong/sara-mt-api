package balancika.ame.entities.tansaction;

import balancika.ame.entities.maintain.Item;
import balancika.ame.entities.setting.Location;
import balancika.ame.entities.setting.Uom;

public class ICTransferDetail {
	
	private String trfId;
	private int lineNo;
	private Item item;
	private Location fLocation;
	private Location tLocation;
	private double trfQty;
	private Uom uom;
	private String postStatus;
	private double retailPrice;
	private double wholeSalePrice;
	public String getTrfId() {
		return trfId;
	}
	public void setTrfId(String trfId) {
		this.trfId = trfId;
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
	public Location getfLocation() {
		return fLocation;
	}
	public void setfLocation(Location fLocation) {
		this.fLocation = fLocation;
	}
	public Location gettLocation() {
		return tLocation;
	}
	public void settLocation(Location tLocation) {
		this.tLocation = tLocation;
	}
	public double getTrfQty() {
		return trfQty;
	}
	public void setTrfQty(double trfQty) {
		this.trfQty = trfQty;
	}
	public Uom getUom() {
		return uom;
	}
	public void setUom(Uom uom) {
		this.uom = uom;
	}
	public String getPostStatus() {
		return postStatus;
	}
	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}
	public double getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
	public double getWholeSalePrice() {
		return wholeSalePrice;
	}
	public void setWholeSalePrice(double wholeSalePrice) {
		this.wholeSalePrice = wholeSalePrice;
	}
	
}
