package balancika.ame.entities.tansaction;

import java.sql.Date;
import java.util.List;

public class ICAdjustment {
	private String adjId;
	private Date adjDate;
	private String adjReference;
	private String adjDesc;
	private String postStatus;
	private String jId;
	private List<ICAdjustmentDetail> adjDetail;
	public String getAdjId() {
		return adjId;
	}
	public void setAdjId(String adjId) {
		this.adjId = adjId;
	}
	public Date getAdjDate() {
		return adjDate;
	}
	public void setAdjDate(Date adjDate) {
		this.adjDate = adjDate;
	}
	public String getAdjReference() {
		return adjReference;
	}
	public void setAdjReference(String adjReference) {
		this.adjReference = adjReference;
	}
	public String getAdjDesc() {
		return adjDesc;
	}
	public void setAdjDesc(String adjDesc) {
		this.adjDesc = adjDesc;
	}
	public String getPostStatus() {
		return postStatus;
	}
	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}
	public String getjId() {
		return jId;
	}
	public void setjId(String jId) {
		this.jId = jId;
	}
	public List<ICAdjustmentDetail> getAdjDetail() {
		return adjDetail;
	}
	public void setAdjDetail(List<ICAdjustmentDetail> adjDetail) {
		this.adjDetail = adjDetail;
	}
	
	
}
