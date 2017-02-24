package balancika.ame.entities.maintain;

import balancika.ame.entities.setting.VendorGroup;

public class Vendor {
	String vendorId;
	String vendorName;
	String contactName;
	String tel1;
	String tel2;
	String address;	
	VendorGroup vendorGroup;
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public VendorGroup getVendorGroup() {
		return vendorGroup;
	}
	public void setVendorGroup(VendorGroup vendorGroup) {
		this.vendorGroup = vendorGroup;
	}
	public Vendor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Vendor(String vendorId, String vendorName) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
	}
	
}
