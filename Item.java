public class Item {
	private String ItemDescription;
	private String Name;
	private int weight;
	
	public Item (String Name ,String ItemDescription, int weight){
		this.Name = Name;
		this.ItemDescription = ItemDescription;
		this.weight = weight;
	}
	
	public String LongItemDescription(){
		return "you see a "+ ItemDescription + " lying on the ground";
	}
	public int getWeight(){
		return weight;
	}
	
	public String getName(){
		return this.Name;
		
	}
 }
