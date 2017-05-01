package balancika.ame.entities.setting;

public class VendorGroup {
	private String systemCode;
	private String vendorGroupId;
	private String vendorGroupName;
	private String remark;
	private Account account;
	public String getSystemCode() {
		return systemCode;
	}
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	public String getVendorGroupId() {
		return vendorGroupId;
	}
	public void setVendorGroupId(String vendorGroupId) {
		this.vendorGroupId = vendorGroupId;
	}
	public String getVendorGroupName() {
		return vendorGroupName;
	}
	public void setVendorGroupName(String vendorGroupName) {
		this.vendorGroupName = vendorGroupName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
}
