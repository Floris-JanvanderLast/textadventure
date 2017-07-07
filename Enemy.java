public class Enemy {
	private Inventory inventory;
	private String EnemyDescription;
	private String Name;
	
	public Enemy(String Name ,String EnemyDescription){
		inventory = new Inventory();
		this.Name = Name;
		this.EnemyDescription = EnemyDescription;
		
	}
	public String LongEnemyDescription(){
		return "You see a " + EnemyDescription;
		
	}
	public String getEnemyName(){
		return this.Name;
		
	}
}
