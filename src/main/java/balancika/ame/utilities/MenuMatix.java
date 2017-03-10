package balancika.ame.utilities;

public class MenuMatix {
	
	private String menuId;
	private String name;
	private String link;
	private String parent;
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public MenuMatix(String menuId, String name, String link, String parent) {
		super();
		this.menuId = menuId;
		this.name = name;
		this.link = link;
		this.parent = parent;
	}
	public MenuMatix() {
		super();
	}		
}
