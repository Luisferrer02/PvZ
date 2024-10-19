package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;

public abstract class Plant extends GameObject{

	private String plantName;
	private int damage;
	protected int hp;
	private int cost;
	
	public Plant() {}
	public Plant(int cost, int dam, int hp, String s, int x, int y, GameWorld game) {
		super(game, x, y);
		this.plantName = s;
		this.cost = cost;
		this.damage = dam;
		this.hp = hp;
	}
	
	@Override
	public boolean isAlive() {
		if(this.hp <= 0) {
			return false;
		}
		return true;
	}
	
	public boolean matchPlant(String s) {
		String symbol = getSymbol();
		String name = getName();
		return symbol.equalsIgnoreCase(s) || name.equalsIgnoreCase(s);
	}
	
	public abstract String getName();
	public abstract int getCost();
	public abstract int getDamage();
	public abstract Plant create(GameWorld game, int x, int y);
	
	public int getHp() {
		return this.hp;		
	}
	
	@Override
	protected void receiveDamage(int d) {
		this.hp = hp - d;
	}
	
	@Override
	public boolean receiveZombieAttack(int damage) {
		this.receiveDamage(damage);
		return true;
	}
	
	@Override
	public boolean receivePlantAttack(int damage) {
		return false;
	}
	
	@Override
	public boolean fillPosition() {
		return true;
	}
	
	@Override
	public boolean catchObject() {
		return false;
	}
	

}
