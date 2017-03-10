package balancika.ame.entities;

import java.util.List;

import balancika.ame.entities.maintain.Employee;

public class AuthorizationEmployee {
	private String empId;
	private String authProcess;
	private String authId;
	private String authName;
	private int action;
	private List<Employee> empDetail;
	
	
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public List<Employee> getEmpDetail() {
		return empDetail;
	}
	public void setEmpDetail(List<Employee> empDetail) {
		this.empDetail = empDetail;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getAuthProcess() {
		return authProcess;
	}
	public void setAuthProcess(String authProcess) {
		this.authProcess = authProcess;
	}
	public String getAuthId() {
		return authId;
	}
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	
	
	
	
}
