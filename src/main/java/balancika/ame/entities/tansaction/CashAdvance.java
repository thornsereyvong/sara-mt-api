package balancika.ame.entities.tansaction;

import java.sql.Date;

import balancika.ame.entities.maintain.Employee;
import balancika.ame.entities.setting.Account;
import balancika.ame.entities.setting.Class;

public class CashAdvance {
	
	private String caId;
	private String caReference;
	private Date caDate;
	private Date expectedClearDate;
	private String remark;
	private String postStatus;
	private String clearanceStatus;	
	private Employee employee;
	private Class classCode;
	private int jId;
	private double amount;
	private Account cashAccount;
	private Account cashAdvanceAccount;

	private double clearAmount;
	
	public String getCaId() {
		return caId;
	}
	public void setCaId(String caId) {
		this.caId = caId;
	}
	public String getCaReference() {
		return caReference;
	}
	public void setCaReference(String caReference) {
		this.caReference = caReference;
	}
	public Date getCaDate() {
		return caDate;
	}
	public void setCaDate(Date caDate) {
		this.caDate = caDate;
	}
	public Date getExpectedClearDate() {
		return expectedClearDate;
	}
	public void setExpectedClearDate(Date expectedClearDate) {
		this.expectedClearDate = expectedClearDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPostStatus() {
		return postStatus;
	}
	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}
	public String getClearanceStatus() {
		return clearanceStatus;
	}
	public void setClearanceStatus(String clearanceStatus) {
		this.clearanceStatus = clearanceStatus;
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
	public int getjId() {
		return jId;
	}
	public void setjId(int jId) {
		this.jId = jId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Account getCashAccount() {
		return cashAccount;
	}
	public void setCashAccount(Account account) {
		this.cashAccount = account;
	}	
	public Account getCashAdvanceAccount() {
		return cashAdvanceAccount;
	}
	public void setCashAdvanceAccount(Account cashAdvanceAccount) {
		this.cashAdvanceAccount = cashAdvanceAccount;
	}
	public double getClearAmount() {
		return clearAmount;
	}
	public void setClearAmount(double clearAmount) {
		this.clearAmount = clearAmount;
	}
	
	
	
}
