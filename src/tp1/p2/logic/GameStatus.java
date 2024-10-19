package tp1.p2.logic;

import tp1.p2.control.Level;

public interface GameStatus {

	int getCycle();

	int getSuncoins();
	
	int getRemainingZombies();

	String positionToString(int col, int row);
	
	int getGeneratedSuns();
	
	int getCaughtSuns();

	boolean isPlayerQuits();

	boolean allZombiesDied();
	
	boolean isPlayerDead();
	
	Level getLevel();
	
	long getSeed();
	
	boolean getReset();
	
	int getPoints();

}
