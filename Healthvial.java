public class Healthvial extends Item {
	
	public Healthvial(String Name, String ItemDescription) {
		super(Name, ItemDescription, 2 );
		

	}
	public void usePotion(){
		System.out.println("you used your health vial");
	}

}
