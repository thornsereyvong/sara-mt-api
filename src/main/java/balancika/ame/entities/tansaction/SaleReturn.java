package balancika.ame.entities.tansaction;

import java.sql.Date;

public class SaleReturn {
	private String saleReturnId;
	private Sale sale;
	private Date saleReturnDate;
	public String getSaleReturnId() {
		return saleReturnId;
	}
	public void setSaleReturnId(String saleReturnId) {
		this.saleReturnId = saleReturnId;
	}
	public Sale getSale() {
		return sale;
	}
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	public Date getSaleReturnDate() {
		return saleReturnDate;
	}
	public void setSaleReturnDate(Date saleReturnDate) {
		this.saleReturnDate = saleReturnDate;
	}
	
	
	
}
