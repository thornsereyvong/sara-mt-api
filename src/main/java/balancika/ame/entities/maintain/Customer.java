package balancika.ame.entities.maintain;

public class Customer {
	private String custId;
	private String custName;
	
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public Customer() {
		super();
	}
	public Customer(String custId, String custName) {
		super();
		this.custId = custId;
		this.custName = custName;
	}
}
