public class Enemy {
	//private Inventory inventory;
	private String Name;
	private int health;
	private int damage;
	
	public Enemy(String name){
		this.Name = name;
		health = 10;
		damage = 5;
		
	}
		public void isAlive(){
			if (getEnemyHealth() <= 0){
				System.out.println("the bandit screams as you cut his arm of.");
				System.exit(0);
				
			}
	}
	public int enemydealDamage(){
		return damage;
		}
	
	private int getEnemyHealth() {
		return health;
		}
	
	private void setEnemyHealth(int health) {
		this.health = health;
	}
	
	public String getEnemyName(){
		return this.Name;
		
	}
}
