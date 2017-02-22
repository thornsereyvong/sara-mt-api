package balancika.ame.entities;

import java.util.List;

public class AuthorizationDetail {
	
	private String authId;
	private String authEmpId;
	private String authGroupId;
	private String authGroupAndOr;
	private String authGroupAmount;
	private List<AuthorizationDetail> authDetail;
	
	public List<AuthorizationDetail> getAuthDetail() {
		return authDetail;
	}
	public void setAuthDetail(List<AuthorizationDetail> authDetail) {
		this.authDetail = authDetail;
	}
	public String getAuthId() {
		return authId;
	}
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	public String getAuthEmpId() {
		return authEmpId;
	}
	public void setAuthEmpId(String authEmpId) {
		this.authEmpId = authEmpId;
	}
	public String getAuthGroupId() {
		return authGroupId;
	}
	public void setAuthGroupId(String authGroupId) {
		this.authGroupId = authGroupId;
	}
	public String getAuthGroupAndOr() {
		return authGroupAndOr;
	}
	public void setAuthGroupAndOr(String authGroupAndOr) {
		this.authGroupAndOr = authGroupAndOr;
	}
	public String getAuthGroupAmount() {
		return authGroupAmount;
	}
	public void setAuthGroupAmount(String authGroupAmount) {
		this.authGroupAmount = authGroupAmount;
	}
	
	
	
}
