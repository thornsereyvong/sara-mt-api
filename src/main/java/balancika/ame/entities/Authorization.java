package balancika.ame.entities;

import java.util.List;

public class Authorization {
	private String authId;
	private String authName;
	private String authType;
	private String authAndOr;
	private String authAmount;
	private List<AuthorizationDetail> authorizationDetail;
	
	
	public List<AuthorizationDetail> getAuthorizationDetail() {
		return authorizationDetail;
	}
	public void setAuthorizationDetail(List<AuthorizationDetail> authorizationDetail) {
		this.authorizationDetail = authorizationDetail;
	}
	public String getAuthId() {
		return authId;
	}
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getAuthAndOr() {
		return authAndOr;
	}
	public void setAuthAndOr(String authAndOr) {
		this.authAndOr = authAndOr;
	}
	public String getAuthAmount() {
		return authAmount;
	}
	public void setAuthAmount(String authAmount) {
		this.authAmount = authAmount;
	}
	
	
}
