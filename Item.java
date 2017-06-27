import java.util.HashMap;

public class Item {
	private String ItemDescription;
	private String Name;
	
	public Item (String Name ,String ItemDescription){
		this.Name = Name;
		this.ItemDescription = ItemDescription;
	}
	
	public String LongItemDescription(){
		return "you see a "+ ItemDescription + " lying on the ground";
	}
	
	public String getName(){
		return this.Name;
		
	}
	
 }
