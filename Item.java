import java.util.HashMap;

public class Item {
	private HashMap<String, Item> items;
	private String ItemDescription;
	private String Name;
	
	public Item (String Name ,String ItemDescription){
		this.Name = Name;
		this.ItemDescription = ItemDescription;
		items = new HashMap<String, Item>();
	}
	
	public String LongItemDescription(){
		return "you see a "+ ItemDescription + " lying on the ground";
	}
	
	public String getName(){
		return this.Name;
		
	}
	
 }
