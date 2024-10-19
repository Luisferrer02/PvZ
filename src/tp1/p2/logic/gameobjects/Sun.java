package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sun extends GameObject {
	
	private GameWorld game;
	private int hp;
	private int col;
	private int row;

	// Remember that a Sun is updated the very same cycle is added to the container
	public static final int SUN_COOLDOWN = 10+1;

	public Sun(GameWorld game, int col, int row) {
		super(game, col, row);
		this.game = game;
		this.col = col;
		this.row = row;
		this.hp = 11;
	}

	@Override
	public boolean catchObject() {
		game.addSuncoins();
		game.addCaughtSuns();
		game.remove(this);
		return true;
	}

	@Override
	public boolean fillPosition() {
		return false;
	}
	
	@Override
	public boolean isAlive() {
		if(this.hp > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public int getHp() {
		return hp;
	}

	@Override
	protected String getSymbol() {
		return Messages.SUN_SYMBOL;
	}
	
	@Override
	public void update() {
		this.hp--;
		
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getDescription() {
		return Messages.SUN_DESCRIPTION;
	}

	
	//de add unimplemented methods pero no sirven
	@Override
	public boolean receiveZombieAttack(int damage) {
		// Auto-generated method stub
		return false;
	}

	@Override
	public boolean receivePlantAttack(int damage) {
		// Auto-generated method stub
		return false;
	}

	@Override
	protected void receiveDamage(int damage) {
		// Auto-generated method stub
		
	}

	@Override
	public int getDamage() {
		// Auto-generated method stub
		return 0;
	}
}
