package balancika.ame.entities.setting;

public class PriceCode {
	private String priceCode;	
	private String des;	
	private int reportPrice;	
	private int defaultPrice;	
	private int specialPrice;
	public String getPriceCode() {
		return priceCode;
	}
	public void setPriceCode(String priceCode) {
		this.priceCode = priceCode;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public int getReportPrice() {
		return reportPrice;
	}
	public void setReportPrice(int reportPrice) {
		this.reportPrice = reportPrice;
	}
	public int getDefaultPrice() {
		return defaultPrice;
	}
	public void setDefaultPrice(int defaultPrice) {
		this.defaultPrice = defaultPrice;
	}
	public int getSpecialPrice() {
		return specialPrice;
	}
	public void setSpecialPrice(int specialPrice) {
		this.specialPrice = specialPrice;
	}
	public PriceCode() {
		super();
	}
	public PriceCode(String priceCode, String des) {
		super();
		this.priceCode = priceCode;
		this.des = des;
	}
	
	
	
}
