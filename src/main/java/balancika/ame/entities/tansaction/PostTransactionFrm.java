package balancika.ame.entities.tansaction;

import java.util.List;

public class PostTransactionFrm {
	String transType;
	String action;
	List<Transaction> transs;
	
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public List<Transaction> getTranss() {
		return transs;
	}
	public void setTranss(List<Transaction> transs) {
		this.transs = transs;
	}
	
}
