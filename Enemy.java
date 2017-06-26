import java.util.HashMap;

public class Enemy {
	private HashMap<String, Enemy> enemy;
	private String EnemyDescription;
	private String Name;
	
	public Enemy(String Name ,String EnemyDescription){
		this.Name = Name;
		enemy = new HashMap<String, Enemy>();
		this.EnemyDescription = EnemyDescription;
		
	}
	public String LongEnemyDescription(){
		return "You see a " + EnemyDescription;
		
	}
	public String getEnemyName(){
		return this.Name;
		
	}
}
