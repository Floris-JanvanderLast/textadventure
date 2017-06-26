import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

	public class Inventory {
	
		private HashMap<String, Item> inventory;
	
	
	public Inventory() {
	
	inventory = new HashMap<String, Item>();
	
	
	}
	public void addItem(String Pickup, Item item){
		inventory.put(Pickup, item);
	}

	public String getItemNames(){
		String str = "";
		for (Item item : inventory.values()) {
			str = "You see a ";
			str += item.getName();
			str += " lying on the ground.\n";
		}
		
		return str;
	}
	public HashMap<String, Item> getInventory(){
		return inventory;
		
	}
	public Entry<String, Item> PickUpItem(String name){
		Iterator it = inventory.entrySet().iterator();
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
		for (Item e : inventory.values()){
			if (i)
		}
		inventory.remove(Drop, item);
		*/
	}	
//}