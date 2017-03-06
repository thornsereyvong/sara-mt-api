package balancika.ame.entities.tansaction;

import java.sql.Date;
import java.util.List;

import balancika.ame.entities.maintain.Employee;
import balancika.ame.entities.setting.Account;
import balancika.ame.entities.setting.Class;

public class CashAdvanceClearance {
	private String clId, remark, reference, postStatus;
	private double amount;
	private Date clearDate;
	private Employee employee;
	private Class classCode;
	private Account account;
	private List<CashAdvanceClearanceDetail> cashAdvanceClearances;
			
	public List<CashAdvanceClearanceDetail> getCashAdvanceClearances() {
		return cashAdvanceClearances;
	}
	public void setCashAdvanceClearances(List<CashAdvanceClearanceDetail> cashAdvanceClearances) {
		this.cashAdvanceClearances = cashAdvanceClearances;
	}
	public String getClId() {
		return clId;
	}
	public void setClId(String clId) {
		this.clId = clId;
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
	public String getPostStatus() {
		return postStatus;
	}
	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getClearDate() {
		return clearDate;
	}
	public void setClearDate(Date clearDate) {
		this.clearDate = clearDate;
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
	
	
	
}
