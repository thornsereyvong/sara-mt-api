package balancika.ame.entities.tansaction;

import java.sql.Date;
import java.util.List;

public class Journal {
	private int jId, isExpense;
	private String reference, postStatus, remark, type, clientId,fromJId;
	private Date jDate;
	private double total;
	private List<JournalDetail> journalDetail;
	public int getjId() {
		return jId;
	}
	public void setjId(int jId) {
		this.jId = jId;
	}
	public int getIsExpense() {
		return isExpense;
	}
	public void setIsExpense(int isExpense) {
		this.isExpense = isExpense;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getFromJId() {
		return fromJId;
	}
	public void setFromJId(String fromJId) {
		this.fromJId = fromJId;
	}
	public Date getjDate() {
		return jDate;
	}
	public void setjDate(Date jDate) {
		this.jDate = jDate;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public List<JournalDetail> getJournalDetail() {
		return journalDetail;
	}
	public void setJournalDetail(List<JournalDetail> journalDetail) {
		this.journalDetail = journalDetail;
	}
	
	
	
}
