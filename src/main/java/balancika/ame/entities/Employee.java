package balancika.ame.entities;

public class Employee {

	private String empID;
	private String empName;
	private String empPostion;
	private String empEmail;
	private boolean statusCheck;
	
	
	
	public boolean isStatusCheck() {
		return statusCheck;
	}
	public void setStatusCheck(boolean statusCheck) {
		this.statusCheck = statusCheck;
	}
	
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpPostion() {
		return empPostion;
	}
	public void setEmpPostion(String empPostion) {
		this.empPostion = empPostion;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	
	
	
	
	
	
}
