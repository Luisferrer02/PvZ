package tp1.p2.logic;

import static tp1.p2.view.Messages.error;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import tp1.p2.control.Command;
import tp1.p2.control.Level;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.InvalidPositionException;
import tp1.p2.control.exceptions.NotCatchablePositionException;
import tp1.p2.control.exceptions.NotEnoughCoinsException;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.Sun;
import tp1.p2.view.Messages;

public class Game implements GameStatus, GameWorld{

	//Atributos
	
	private long seed;
	private Level level;
	private int cycle;
	private GameObjectContainer container;
	private Deque<GameAction> actions;
	private boolean playerQuits;
	private boolean isFinished;
    private ZombiesManager ZM;
	private Random rand;
	private SunsManager SM;
	private int suncoins;
	private boolean reset;
	private Record record;
	private int puntuacion;
	
	//Constr.
	
	public Game(long seed, Level level) throws GameException {
		this.seed = seed;
		this.level = level;
		this.container = new GameObjectContainer();
		reset();
	}
	
	//Metodos
	
	public void playerQuits() {
		this.playerQuits = true;
	}
	
	@Override
	public void reset() throws GameException {
		reset(this.level, this.seed);
	}
	
	@Override
	public void reset(Level level, long seed) throws GameException{
		System.out.println(String.format(Messages.CONFIGURED_LEVEL, level.name()));
		System.out.println(String.format(Messages.CONFIGURED_SEED, seed));
		this.seed = seed;
		this.rand = new Random(this.seed);
		this.level = level;
		this.isFinished = false;
    	this.playerQuits = false;
    	this.ZM = new ZombiesManager(this, level, rand);
    	this.SM = new SunsManager(this ,rand);
    	this.container = new GameObjectContainer();
    	this.cycle = 0;
		this.actions = new ArrayDeque<>();
		this.suncoins = 50;
		this.reset = true;
		this.record = record.loadRecord(level);
		this.puntuacion = 0;
		
	}
	
public void writeRecord(){
		
		if (puntuacion > record.getPoints()) {
			try {
				System.out.println(Messages.NEW_RECORD +  puntuacion);
				record.writeRecord(puntuacion);
			} catch (GameException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void update() throws GameException{
		// 1. Execute pending actions
				executePendingActions();

				// 2. Execute game Actions
				

				// 3. Game object updates
				ZM.update();
				SM.update();

				container.update();
			
				// 4. & 5. Remove dead and execute pending actions
				boolean deadRemoved = true;
				while (deadRemoved|| areTherePendingActions()) {
					// 4. Remove dead
			
					deadRemoved = this.container.removeDead();

					// 5. execute pending actions
					executePendingActions();
				}

				this.cycle++;
				
				if (allZombiesDied()) {
					this.isFinished = true;
				}else if (isPlayerDead()) {
					this.isFinished = true;
				}

				// 6. Notify commands that a new cycle started
				Command.newCycle();
				
				//Update record

	}
	private void executePendingActions() {
		while (!this.actions.isEmpty()) {
			GameAction action = this.actions.removeLast();
			action.execute(this);
		}
	}
	
	private boolean areTherePendingActions() {
		return this.actions.size() > 0;
	}
	
	@Override
	public boolean isFullyOcuppied(int col, int row) {
		return this.container.isFullyOccupied(col, row);
	}
	
	@Override
	public void addSun() {
		SM.addSun();
	}
	
	@Override
	public void tryToCatchObject(int col, int row) throws GameException {
	    GameItem g = this.getNotFilledObject(col, row);
	    if (g == null) {
	        throw new NotCatchablePositionException(Messages.NO_CATCHABLE_IN_POSITION.formatted(col, row));
	    }
	    while (g != null) {
	        g.catchObject();
	        g = this.getNotFilledObject(col, row);
	    }
	}


	private GameItem getNotFilledObject(int col, int row) {
		return container.getNotFilledObject(col, row);
	}

	@Override
	public void addItem(GameObject g) {
		this.container.add(g);
		
	}
	
	@Override
	public int getCycle() {
		return this.cycle;
	}

	@Override
	public int getSuncoins() {
		return this.suncoins;
	}

	@Override
	public int getRemainingZombies() {
		return ZM.getRemainingZombies();
	}

	@Override
	public String positionToString(int col, int row) {
		return container.positionToString(col,row);
	}
	
	
	public boolean execute(Command command) throws GameException {
		return command.execute(this);
	}

	public boolean isFinished() {
		
		return isFinished;
	}

	public boolean isPlayerQuits() {
		return this.playerQuits;
	}

	@Override
	public boolean allZombiesDied() {
		if (ZM.getRemainingZombies() == 0 && ZM.getAlivedZombies() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public void consumeCoins(int cost) {
		this.suncoins = this.suncoins-cost;
		
	}


	@Override
	public GameItem getGameItemInThisPos(int x, int y) {
		return container.getGameObjectPos(x, y);
	}

	@Override
	public void addSuncoins() {
		this.suncoins = this.suncoins + 10;
	}
	
	@Override
	public void remove(Sun sun) {
		container.remove(sun);
	}



	/*@Override
	public void remove(GameItem g) {
		container.remove(g);
	}*/

	/*@Override
	public void pushAction(GameAction action) {
		this.actions.addLast(action);
	}*/
	
	@Override
	public void pushAction(ExplosionAction explosionAction) {
		this.actions.addLast(explosionAction);
		
	}

	/*@Override
	public int GetSuncoins() {
		return this.suncoins;
	}*/

	/*@Override
	public GameItem getSun(int x, int y) {
		return container.getSun(x, y);
	}*/

	@Override
	public boolean isPlayerDead() {
		return container.isPlayerDead();
	}

	@Override
	public Level getLevel() {
		return this.level;
	}

	@Override
	public long getSeed() {
		return this.seed;
	}

	@Override
	public boolean getReset() {
		return this.reset;
	}
	
	public void NotReset() {
		this.reset = false;
	}

	@Override
	public void deadZombie() {
		this.ZM.deadZombie();
		
	}

/*	@Override
	public void addZombieAlived() {
		this.ZM.addZombieAlived();
		
	}*/

	public static boolean isNumeric(String str) {
		boolean resultado;
		try {
			Integer.parseInt(str);
			resultado = true;
		} catch (NumberFormatException excepcion) {
			resultado = false;
		}
		return resultado;
	}

	
	@Override
	public void addCaughtSuns() {
		this.SM.addCaughtSuns();
		
	}

	@Override
	public int getGeneratedSuns() {
		return SM.getGeneratedSuns();
	}

	@Override
	public int getCaughtSuns() {
		return SM.getCaughtSuns();
	}

	/*@Override
	public void addItem(Sun sun) {
		container.add(sun);
		
	}*/

	@Override
	public void checkValidPlantPosition(int col, int row) throws GameException {
		if (col >= 8 || col < 0 || row < 0|| row >= 4) {
			throw new InvalidPositionException (Messages.INVALID_POSITION.formatted(col,row));
		}
		
	}

	@Override
	public void checkValidZombiePosition(int col, int row) throws GameException {
		if (col >= 9 || col < 0 || row < 0|| row >= 4) {
			throw new InvalidPositionException (Messages.INVALID_POSITION.formatted(col,row));
		}
	}

	@Override
	public void tryToBuy(int cost) throws GameException {
		if(suncoins < cost) {
			throw new NotEnoughCoinsException(Messages.NOT_ENOUGH_COINS);
		}	
		
	}

	@Override
	public int getPoints() {
		return this.puntuacion;
	}



	
	@Override
	public void addPuntos(int x) {
		this.puntuacion += x;
	} 
	
	@Override
	public String getLevelName() {
		return this.level.name();	}

	@Override
	public Record getRecord() {
		return this.record;
	}

	
	
}
