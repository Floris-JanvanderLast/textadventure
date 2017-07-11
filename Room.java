import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

class Room
{
	private Inventory inventory;
	private List<Enemy>enemylist;
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court
     * yard".
     */
    public Room(String description)
    {
    	inventory = new Inventory();
        this.description = description;
        exits = new HashMap<String, Room>();
        enemylist = new ArrayList<Enemy>();
        }

    /**
     * Define an exit from this room.
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }
    public void spawnEnemy(){
    	Enemy enemy = new Enemy("Bandit");
    	enemylist.add(enemy);
    	System.out.println("you see a " + enemy);
    }


    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a long description of this room, in the form:
     *     You are in the kitchen.
     *     Exits: north west
     */
    public String getLongDescription()
    {
        return "\nYou are " + description +".\n" + inventory.getItemNames() + "\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     */
    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(Iterator<String> iter = keys.iterator(); iter.hasNext(); )
            returnString += " " + iter.next();
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
    public Room getExit(String direction)
    {
        return (Room)exits.get(direction);
    }
    public void setRoom(){
 		this.exits = exits;
 	}
    public HashMap<String, Room> getRoom(){
    	return exits;
    }
    

	public Inventory getInventory() {
		return inventory;
	}

	/*public void attackRandomEnemy() {
		int i = Math.floor(Math.random() * (enemylist.size() - 1) - 0);
		
	}*/
}
 	