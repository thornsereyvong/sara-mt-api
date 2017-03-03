package balancika.ame.entities.tansaction;

import java.sql.Date;

import balancika.ame.entities.maintain.Employee;
import balancika.ame.entities.setting.Account;
import balancika.ame.entities.setting.Class;
import balancika.ame.entities.setting.Company;

public class CashTransfer {
	
	private String ctfId;
	private String reference;
	private Date ctfDate;
	private String ctfType;
	private String postStatus;
	private Employee employee;
	private Class classCode;
	private Account account;
	private String remark;
	private Company company;
	public String getCtfId() {
		return ctfId;
	}
	public void setCtfId(String ctfId) {
		this.ctfId = ctfId;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Date getCtfDate() {
		return ctfDate;
	}
	public void setCtfDate(Date ctfDate) {
		this.ctfDate = ctfDate;
	}
	public String getCtfType() {
		return ctfType;
	}
	public void setCtfType(String ctfType) {
		this.ctfType = ctfType;
	}
	public String getPostStatus() {
		return postStatus;
	}
	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Class getClassCode() {
		return classCode;
	}
	public void setClassCode(Class classCode) {
		this.classCode = classCode;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	
	
}
