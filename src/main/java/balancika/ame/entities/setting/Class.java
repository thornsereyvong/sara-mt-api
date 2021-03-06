package balancika.ame.entities.setting;

public class Class {
	private String classId;
	private String className;
	private int inactive=0;
	
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getInactive() {
		return inactive;
	}
	public void setInactive(int inactive) {
		this.inactive = inactive;
	}
	public Class(String classId, String className, int inactive) {
		super();
		this.classId = classId;
		this.className = className;
		this.inactive = inactive;
	}
}
