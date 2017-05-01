package balancika.ame.entities.setting;

public class Account {
	private String accountId;
	private String accountName;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Account(String accountId, String accountName) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
	}
	public Account() {
		super();
	}
	
}
