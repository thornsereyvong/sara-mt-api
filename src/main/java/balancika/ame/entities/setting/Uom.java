package balancika.ame.entities.setting;

public class Uom {
	private String systemCodeId;
	private String uomId;
	private String description;
	private String remark;
	
	public String getSystemCodeId() {
		return systemCodeId;
	}
	public void setSystemCodeId(String systemCodeId) {
		this.systemCodeId = systemCodeId;
	}
	public String getUomId() {
		return uomId;
	}
	public void setUomId(String uomId) {
		this.uomId = uomId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Uom() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Uom(String uomId, String description) {
		super();
		this.uomId = uomId;
		this.description = description;
	}
	
}
