import java.util.Map.Entry;

/**
 *  This class is the main class of the "World of Zuul" application.
 *  "World of Zuul" is a very simple, text based adventure game.  Users
 *  can walk around some scenery. That's all. It should really be extended
 *  to make it more interesting!
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Game
{
    private Parser parser;
    private Player player;
    private Room forest, mainHall, camp ,tower, basement, dungeon, hell, eatingHall, tent;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
    	player = new Player();
        createRooms();
        createItems();
        createEnemies();
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
        tower = new Room ("on a tower, you see a foggy forest");
        basement = new Room ("in the basement of a tower");
        dungeon =  new Room ("in a room you see a demon standing at a door");
        hell = new Room ("in HELL there is a big gate which looks like an entrance");

        // initialise room exits
        forest.setExit("east", camp);
        forest.setExit("north", mainHall);
        forest.setExit("west", eatingHall);

        mainHall.setExit("south", forest);
        mainHall.setExit("up", tower);
        mainHall.setExit("down", basement);
        
        tower.setExit("down", mainHall);
        basement.setExit("up", mainHall);
        
        dungeon.setExit("up" , basement);
        dungeon.setExit("north", hell);
        hell.setExit("south", dungeon);
        basement.setExit("down", dungeon);

        eatingHall.setExit("east", forest);

        camp.setExit("west", forest);
        camp.setExit("east", tent);

        tent.setExit("west", camp);

        player.setCurrentRoom(forest);  // start game outside
    }
    
    private void createItems(){
    	Item healthPotion , steelSword, Ring;
    	
    	//create the items
    	
    	
    	Ring = new Item ("Ring", "ring");
    	steelSword = new Item("Sword", "steel sword");
    	healthPotion = new Item("HealthVial","healing potion");
    	Inventory basementInv = basement.getInventory();
    	Inventory tentInv = tent.getInventory();
    	Inventory dungeonInv = dungeon.getInventory();
    	Inventory outsideInv = forest.getInventory();
    	dungeonInv.addItem("healthvial", healthPotion);
    	basementInv.addItem("Ring", Ring);
    	tentInv.addItem("sword", steelSword);
    }
    private void createEnemies(){
    	Enemy demon;
    	
    	//create the enemies
    	
    	demon = new Enemy("Demon", "demon");
    	
    	
    	
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
        System.out.println("Welcome to game!");
        System.out.println("Game is a new, brutal adventure game.");
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
        	player.getInventory().addItem(hash.getKey(), hash.getValue());
        }
   //     else if (commandWord.equals("drop")){
        //	Inventory RoomInv = player.getCurrentRoom().getInventory();
        	//RoomInv.removeItem(command.getSecondWord());
      //  }
        else if (commandWord.equals("suicide")){
        	System.out.println("You made the choice to scratch your veins with your nails and you killed yourself.");
        	System.exit(0);
        }
        	
        else if (commandWord.equals("go")){
            goRoom(command);
        }
        else if (commandWord.equals("quit")){
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("inventory")){
        	System.out.println(player.lookInventory());
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
