package tp1.p2.logic.gameobjects;

import static tp1.p2.view.Messages.status;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

/**
 * Base class for game non playable character in the game.
 *
 */
public abstract class GameObject implements GameItem {

	protected GameWorld game;

	protected int col;

	protected int row;


	GameObject() {
	}

	GameObject(GameWorld game, int col, int row) {
		this.game = game;
		this.col = col;
		this.row = row;
	}

	public boolean isInPosition(int col, int row) {
		return this.col == col && this.row == row;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}
	
	abstract public boolean isAlive();

	public String toString() {
		if (isAlive()) {
			return Messages.GAME_OBJECT_STATUS.formatted(this.getSymbol(), this.getHp());
		} else {
			return "";
		}
	}

	abstract protected String getSymbol();

	abstract public String getDescription();

	abstract public void update();
	
	abstract public void onEnter();
	
	abstract public void onExit();
	
	abstract public int getHp();
	
	public abstract boolean receiveZombieAttack(int damage);
	public abstract boolean receivePlantAttack(int damage);
	abstract protected void receiveDamage(int damage);
	public abstract int getDamage();
	
	
}

