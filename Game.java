import java.util.Map.Entry;

class Game
{
    private Parser parser;
    private Player player;
    private Item item;
    private Enemy enemy;
    private Room forest, mainHall, camp ,towerRoof, library, dungeon, hell, eatingHall, tent, tower;

    /**
     * Create the game and initialize its internal map.
     */
    public Game()
    {
    	player = new Player();
        createRooms();
        createItems();
        parser = new Parser();
        
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
              // create the rooms
        forest = new Room("outside the main entrance of an abandoned castle");
        mainHall = new Room("in the main hall of the castle");
        eatingHall = new Room("in an eating hall");
        camp = new Room("in a camp where you can see a tent");
        tent = new Room("in a tent the inside is covered in blood");
        towerRoof = new Room ("on a tower, you see a foggy forest");
        tower = new Room ("You are in a tower");
        library = new Room ("in a library");
        dungeon =  new Room ("in a room you see a demon standing at a door");
        hell = new Room ("in HELL there is a big gate which looks like an entrance");
        hell.spawnEnemy();

        // Initialize room exits
        forest.setExit("east", camp);
        forest.setExit("north", mainHall);
        forest.setExit("west", eatingHall);
        
        tower.setExit("up", towerRoof);
        tower.setExit("down", library);
        tower.setExit("south", mainHall);


        mainHall.setExit("south", forest);
        mainHall.setExit("north", tower);
        
        towerRoof.setExit("down", mainHall);
        library.setExit("up", mainHall);
        
        dungeon.setExit("up" , library);
        dungeon.setExit("north", hell);
        hell.setExit("south", dungeon);
        library.setExit("down", dungeon);

        eatingHall.setExit("east", forest);

        camp.setExit("west", forest);
        camp.setExit("east", tent);

        tent.setExit("west", camp);

        player.setCurrentRoom(forest);  // start game outside
    }
    
    private void createItems(){
    	Item healthPotion , steelSword, Ring, book;
    	
    	//create the items
    	
    	
    	book = new Item ("Book", "book", 5);
    	//Ring = new Item ("Ring", "ring");
    	steelSword = new Item("Sword", "steel sword", 6);
    	healthPotion = new Item("HealthVial","healing potion", 5);
    	Inventory basementInv = library.getInventory();
    	Inventory tentInv = tent.getInventory();
    	Inventory dungeonInv = dungeon.getInventory();
    //	Inventory outsideInv = forest.getInventory();
    	dungeonInv.addItem("healthvial", healthPotion);
    	basementInv.addItem("book", book);
    	tentInv.addItem("sword", steelSword);
    }
    	
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            if (command != null){
            	finished = processCommand(command);
            }
            
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Save Me!");
        System.out.println("Save Me is a new, brutal adventure game.");
        System.out.println("You wake up in a forest, you look down and see that your armor is drenched in blood");
        System.out.println("You've lost your sword you need to find it in case your attacker returns");
        System.out.println("Type 'help' if you need help.");
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")){
            printHelp();
           }
        else if (commandWord.equals("look")){
        	 System.out.println(player.getCurrentRoom().getLongDescription());
        }
        else if (commandWord.equals("health")){
        	System.out.println(player.getHealth());
        }
        else if (commandWord.equals("heal")){
        	player.heal(10);
        	System.out.println("your health is " + player.getHealth());
        }
        else if (commandWord.equals("take")){
        	Inventory RoomInv = player.getCurrentRoom().getInventory();
        	Entry<String, Item> hash = RoomInv.PickUpItem(command.getSecondWord());
        	if (player.getInventory().getCurrentWeight() + hash.getValue().getWeight() < player.getInventory().getMaxWeight()){
        	    	    	
	        	player.getInventory().addItem(hash.getKey(), hash.getValue());
	        	if (hash.getKey() == "book"){
	        		player.dealDamage(20);
	        		System.out.println("This book is cursed your finger is turning to stone you quickly break it off");
	        		player.isAlive();
        		}
	        	if (hash.getKey() == "sword"){
	        		System.out.println("you hear something behind you");
	        		System.out.println("You feel a cool breeze on your neck, the moment you turned around you got stabed in your stomach.");
	        		System.out.println("You fall to the ground and take your last breath."+ "\n");
	        		System.out.println("Thanks for playing.");
	        		System.exit(0);
	        	}
        	}else{
        		System.out.print("you are currently overweight "+ "\n");
        	}
        }
        else if (commandWord.equals("drop")){

        }
        else if (commandWord.equals("suicide")){
        	System.out.println("You made the choice to scratch your veins with your nails and you killed yourself.");
        	System.exit(0);
        }
        else if (commandWord.equals("attack")){
        	
        }
        	
        else if (commandWord.equals("go")){
            goRoom(command);
        }
        else if (commandWord.equals("quit")){
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("inventory")){
        	System.out.println(player.lookInventory());
        	System.out.println(player.getInventory().getCurrentWeight());
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp()
    {
        System.out.println("You are lost. You dont know what to do. ");
        System.out.println("Is this the end?.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null)
            System.out.println("There is no door!");
        else {
        	player.setCurrentRoom(nextRoom);
        	player.dealDamage(10);
        	player.isAlive();
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }


    public static void main(String[] args)
    {
        Game game = new Game();
        game.play();
    }
}
