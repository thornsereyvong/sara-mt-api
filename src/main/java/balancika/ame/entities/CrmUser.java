package balancika.ame.entities;

import java.util.List;

public class CrmUser {

	private String userID;
	private String username;
	private String password;
	private String userType;
	private String userKey;
	private int status;
	private String parentID;
	private UserApp userApp;
	private MeDataSource dataSource;
	private List<CrmConfDashboard> confDashboard;	
	
	public List<CrmConfDashboard> getConfDashboard() {
		return confDashboard;
	}

	public void setConfDashboard(List<CrmConfDashboard> confDashboard) {
		this.confDashboard = confDashboard;
	}

	public UserApp getUserApp() {
		return userApp;
	}

	public void setUserApp(UserApp userApp) {
		this.userApp = userApp;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
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
