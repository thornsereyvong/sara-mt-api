package balancika.ame.entities;

import java.util.List;


public class AuthorizationGroup {

	private String authGroupId;
	private String authGroupName;
	private String authGroupDesc;
	private String authGroupCount;
	private boolean statusCheck = false;
	public boolean isStatusCheck() {
		return statusCheck;
	}
	public void setStatusCheck(boolean statusCheck) {
		this.statusCheck = statusCheck;
	}
	private List<AuthorizationGroupDetail> authGroupDetail;
	
	public String getAuthGroupId() {
		return authGroupId;
	}
	public String getAuthGroupCount() {
		return authGroupCount;
	}
	public void setAuthGroupCount(String authGroupCount) {
		this.authGroupCount = authGroupCount;
	}
	public void setAuthGroupId(String authGroupId) {
		this.authGroupId = authGroupId;
	}
	public String getAuthGroupName() {
		return authGroupName;
	}
	public void setAuthGroupName(String authGroupName) {
		this.authGroupName = authGroupName;
	}
	public String getAuthGroupDesc() {
		return authGroupDesc;
	}
	public void setAuthGroupDesc(String authGroupDesc) {
		this.authGroupDesc = authGroupDesc;
	}
	public List<AuthorizationGroupDetail> getAuthGroupDetail() {
		return authGroupDetail;
	}
	public void setAuthGroupDetail(List<AuthorizationGroupDetail> authGroupDetail) {
		this.authGroupDetail = authGroupDetail;
	}
	
	
	
	
	
	
}
