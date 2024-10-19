package tp1.p2.logic;

import tp1.p2.control.Level;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.logic.gameobjects.Sun;

public interface GameWorld {

	public static final int NUM_ROWS = 4;

	public static final int NUM_COLS = 8;

	void update() throws GameException;

	void reset() throws GameException;

	void reset(Level level, long seed) throws GameException;

	void tryToBuy(int cost) throws GameException;

	void checkValidPlantPosition(int col, int row) throws GameException;

	void checkValidZombiePosition(int col, int row) throws GameException;

	void tryToCatchObject(int col, int row) throws GameException;

	void addSuncoins();

	void addCaughtSuns();

	void remove(Sun sun);

	void pushAction(ExplosionAction explosionAction);

	void deadZombie();

	GameItem getGameItemInThisPos(int i, int j);

	void addSun();

	void addItem(GameObject g);

	/**
	 * Checks if a cell is fully occupied, that is, the position can be shared between an NPC (Plant, Zombie) and Suns .
	 * 
	 * @param col Column of the cell
	 * @param row Row of the cell
	 * 
	 * @return <code>true</code> if the cell is fully occupied, <code>false</code>
	 *         otherwise.
	 */
	boolean isFullyOcuppied(int col, int row);

	void consumeCoins(int cost);

	void playerQuits();

	void addPuntos(int x);

	String getLevelName();

	Record getRecord();

}