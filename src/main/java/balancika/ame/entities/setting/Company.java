package balancika.ame.entities.setting;

import java.sql.Date;

public class Company {
	
	private String comId;
	private String comName;
	private String remark;
	private String reference;
	private Date createDate;
	
	public String getComId() {
		return comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Company(String comId, String comName) {
		super();
		this.comId = comId;
		this.comName = comName;
	}
	
	
	
}	
