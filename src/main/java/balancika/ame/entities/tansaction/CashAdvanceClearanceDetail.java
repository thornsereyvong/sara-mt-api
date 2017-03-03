package balancika.ame.entities.tansaction;

import balancika.ame.entities.setting.Class;

public class CashAdvanceClearanceDetail {
	private String crId,postStatus,remark;
	private Class classCode;
	private CashAdvance cashAdvance;
	private int lineNo;
	private double clearAmount;
	private int jId;
	public String getCrId() {
		return crId;
	}
	public void setCrId(String crId) {
		this.crId = crId;
	}
	public String getPostStatus() {
		return postStatus;
	}
	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Class getClassCode() {
		return classCode;
	}
	public void setClassCode(Class classCode) {
		this.classCode = classCode;
	}
	public CashAdvance getCashAdvance() {
		return cashAdvance;
	}
	public void setCashAdvance(CashAdvance cashAdvance) {
		this.cashAdvance = cashAdvance;
	}
	public int getLineNo() {
		return lineNo;
	}
	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}
	public double getClearAmount() {
		return clearAmount;
	}
	public void setClearAmount(double clearAmount) {
		this.clearAmount = clearAmount;
	}
	public int getjId() {
		return jId;
	}
	public void setjId(int jId) {
		this.jId = jId;
	}
	
	
	
}
