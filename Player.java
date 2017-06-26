import java.util.Iterator;
import java.util.Set;

class Player {
	 private Inventory inventory;
	 private Room currentRoom;
	 private int health = 50;
	 
	public Player(){
		
		inventory = new Inventory();
		System.out.println(getHealth());		
	 }


	public Room getCurrentRoom() {
		return currentRoom;
	}


	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	public void dealDamage(int damage){
		this.setHealth(this.getHealth() - damage);
	}
	public void isAlive(){
		if (getHealth() <= 0){
			System.out.println("You bled to death!");
			System.exit(0);
			
		}
	}
	private String checkInventory(){
        String returnString = "";
        Set<String> keys = inventory.getInventory().keySet();
        for(String string : keys){
            returnString += " " + string;
        }
        return returnString;
    }
	public String lookInventory(){
		return checkInventory();
	}
	
	public void heal(int heal){
		this.setHealth(this.getHealth() + heal);
	}


	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}
	
	public Inventory getInventory() {
		return inventory;

	}
}