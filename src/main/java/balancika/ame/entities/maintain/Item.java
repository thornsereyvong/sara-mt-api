package balancika.ame.entities.maintain;

import balancika.ame.entities.setting.ItemGroup;

public class Item {
	private String itemId;
	private String itemName;
	private ItemGroup itemGroup;
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public ItemGroup getItemGroup() {
		return itemGroup;
	}
	public void setItemGroup(ItemGroup itemGroup) {
		this.itemGroup = itemGroup;
	}
	public Item(String itemId, String itemName) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
	}
	public Item() {
		super();
	}
	
	
}
