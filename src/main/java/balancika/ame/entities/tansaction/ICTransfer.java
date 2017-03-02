package balancika.ame.entities.tansaction;

import java.sql.Date;
import java.util.List;

import balancika.ame.entities.maintain.Customer;
import balancika.ame.entities.setting.Location;

public class ICTransfer {
	
	private String trfId;
	private String trfReference;
	private Date trfDate;
	private int periodM;
	private int periodY;
	private String trfDescription;
	private String postStatus;
	private int consignment;	
	private Customer customer;
	private Location location;
	private String pRetail;
	private String pWholeSale;
	List<ICTransferDetail> trfDetail;

	public String getTrfId() {
		return trfId;
	}
	public void setTrfId(String trfId) {
		this.trfId = trfId;
	}
	public String getTrfReference() {
		return trfReference;
	}
	public void setTrfReference(String trfReference) {
		this.trfReference = trfReference;
	}
	public Date getTrfDate() {
		return trfDate;
	}
	public void setTrfDate(Date trfDate) {
		this.trfDate = trfDate;
	}
	public int getPeriodM() {
		return periodM;
	}
	public void setPeriodM(int periodM) {
		this.periodM = periodM;
	}
	public int getPeriodY() {
		return periodY;
	}
	public void setPeriodY(int periodY) {
		this.periodY = periodY;
	}
	public String getTrfDescription() {
		return trfDescription;
	}
	public void setTrfDescription(String trfDescription) {
		this.trfDescription = trfDescription;
	}
	public String getPostStatus() {
		return postStatus;
	}
	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}
	public int getConsignment() {
		return consignment;
	}
	public void setConsignment(int consignment) {
		this.consignment = consignment;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getpRetail() {
		return pRetail;
	}
	public void setpRetail(String pRetail) {
		this.pRetail = pRetail;
	}
	public String getpWholeSale() {
		return pWholeSale;
	}
	public void setpWholeSale(String pWholeSale) {
		this.pWholeSale = pWholeSale;
	}
	public List<ICTransferDetail> getTrfDetail() {
		return trfDetail;
	}
	public void setTrfDetail(List<ICTransferDetail> trfDetail) {
		this.trfDetail = trfDetail;
	}
}
