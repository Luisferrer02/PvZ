package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;

public abstract class Zombie extends GameObject{
	private String name;
	private int endurance;
	private int damage;
	private int speed;
	protected int NumCycles = -1;
	private int ZombieIdx;
	
	public Zombie() {}
	public Zombie(String name, int e, int dam, int s, int idx, int x, int y, GameWorld game) {
		super(game, x, y);
		this.endurance = e;
		this.damage = dam;
		this.speed = s;
		this.ZombieIdx = idx;
		
	}
	
	public abstract Zombie create(GameWorld game, int x, int y);
	protected abstract int getIdx();
	
	protected boolean Zmove() {
		NumCycles++;
		if(NumCycles == speed) {
			col--;
			NumCycles = 0;
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean matchZombie(int idx) {
		int ZombieIdx = getIdx();
		return ZombieIdx == idx;
	}
	
	@Override
	public boolean isAlive() {
		if (this.endurance <= 0) {
			return false;
		}
		return true;
	}
	
	@Override
	protected void receiveDamage(int d) {
		this.endurance = endurance - d;
	}
	
	@Override
	public boolean receivePlantAttack(int damage) {
		this.receiveDamage(damage);
		return true;
	}
	
	@Override
	public boolean receiveZombieAttack(int damage) {
		return false;
	}
	
	public int getHp() {
		return this.endurance;
	}
	
	@Override
	public boolean fillPosition() {
		return true;
	}
	
	@Override
	public boolean catchObject() {
		return false;
	}
	
	@Override
	public void update() {
		GameItem g = game.getGameItemInThisPos(this.col - 1, this.row);
		if (g != null && g.fillPosition()) {
			g.receiveZombieAttack(this.damage);
		} 
		else {
			Zmove();
		}
	}
	
	@Override
	public void onExit() {
		game.deadZombie();
	}
	
}
