package balancika.ame.entities;

public class CrmUserLogin{

	private String userID;
	private String username;
	private String password;
	private String type;
	private int status;
	private String parentID;
	private String appId;
	private MeDataSource dataSource;
	private int has;
	private int permission;
	
	
	@Override
	public String toString() {
		return "CrmUserLogin [userID=" + userID + ", username=" + username + ", password=" + password + ", type=" + type
				+ ", status=" + status + ", parentID=" + parentID + ", appId=" + appId + ", dataSource=" + dataSource
				+ ", has=" + has + ", permission=" + permission + "]";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getHas() {
		return has;
	}

	public void setHas(int has) {
		this.has = has;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getParentID() {
		return parentID;
	}
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public MeDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(MeDataSource dataSource) {
		this.dataSource = dataSource;
	}
}
