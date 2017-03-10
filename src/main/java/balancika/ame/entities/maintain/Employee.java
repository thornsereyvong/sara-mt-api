package balancika.ame.entities.maintain;

public class Employee {

	private String empId;
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
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
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
	public Employee() {
		super();
	}
	public Employee(String empId, String empName) {
		super();
		this.empId = empId;
		this.empName = empName;
	}
	
}
