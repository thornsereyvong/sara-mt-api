package balancika.ame.entities.tansaction;

import java.sql.Date;

import balancika.ame.entities.setting.Account;
import balancika.ame.entities.setting.Class;

public class JournalDetail {
	private int jId,lineNo,periodY,perriodM, periodD, clearStatus;
	private String drCr,reference, postStatus, docType, clientId;
	private Date jDate;
	private double amount;
	private Account account;
	private Class classCode;
	
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
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
	public int getLineNo() {
		return lineNo;
	}
	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}
	public int getPeriodY() {
		return periodY;
	}
	public void setPeriodY(int periodY) {
		this.periodY = periodY;
	}
	public int getPerriodM() {
		return perriodM;
	}
	public void setPerriodM(int perriodM) {
		this.perriodM = perriodM;
	}
	public int getPeriodD() {
		return periodD;
	}
	public void setPeriodD(int periodD) {
		this.periodD = periodD;
	}
	public int getClearStatus() {
		return clearStatus;
	}
	public void setClearStatus(int clearStatus) {
		this.clearStatus = clearStatus;
	}
	public String getDrCr() {
		return drCr;
	}
	public void setDrCr(String drCr) {
		this.drCr = drCr;
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
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public Date getjDate() {
		return jDate;
	}
	public void setjDate(Date jDate) {
		this.jDate = jDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
}
