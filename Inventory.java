import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

	public class Inventory {
	
		private HashMap<String, Item> itemList;
		private int maxWeight;
		private int currentWeight;
		private Item item;
	
	
	public Inventory() {
	
		itemList = new HashMap<String, Item>();
		maxWeight = 15;
		currentWeight = 0;
	
	
	}
	public void addItem(String Pickup, Item item){
		itemList.put(Pickup, item);
		currentWeight += item.getWeight();
		}
	public int getCurrentWeight(){
		return currentWeight;
	}
	public int getMaxWeight(){
		return maxWeight;
	}

	public String getItemNames(){
		String str = "";
		for (Item item : itemList.values()) {
			str = "You see a ";
			str += item.getName();
			str += " lying on the ground.\n";
		}
		
		return str;
	}
	
	public HashMap<String, Item> getItemList(){
		return itemList;
		}
		
	
	public Entry<String, Item> PickUpItem(String name){
		Iterator it = itemList.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Item> hash = (Entry<String, Item>) it.next();
			Item i = (Item) hash.getValue();
			if (i.getName().equalsIgnoreCase(name)){
				Item item = i;
				String key = hash.getKey();
				it.remove();
				System.out.println("you have picked up the " + item.getName());
				return hash;
		  }
	  }			
		return null;
   }

	/*public void removeItem(String Drop, Item item) {
		for (Item e : itemList.values()){
		}
		itemList.remove(Drop, item);
		
	}*/	
}