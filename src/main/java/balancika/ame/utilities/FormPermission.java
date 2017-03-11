package balancika.ame.utilities;

public class FormPermission {
	private String userId, form;
	private boolean pread, padd, pedit, pdelete, ppost, pvoid;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public boolean isPread() {
		return pread;
	}

	public void setPread(boolean pread) {
		this.pread = pread;
	}

	public boolean isPadd() {
		return padd;
	}

	public void setPadd(boolean padd) {
		this.padd = padd;
	}

	public boolean isPedit() {
		return pedit;
	}

	public void setPedit(boolean pedit) {
		this.pedit = pedit;
	}

	public boolean isPdelete() {
		return pdelete;
	}

	public void setPdelete(boolean pdelete) {
		this.pdelete = pdelete;
	}

	public boolean isPpost() {
		return ppost;
	}

	public void setPpost(boolean ppost) {
		this.ppost = ppost;
	}

	public boolean isPvoid() {
		return pvoid;
	}

	public void setPvoid(boolean pvoid) {
		this.pvoid = pvoid;
	}	
	
}
