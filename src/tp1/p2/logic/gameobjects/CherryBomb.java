package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class CherryBomb extends Plant{
	
	//Atributos
	private static int cost = 50;
	private static int damage = 10;
	private static int endurance = 2;
	private static String plantName = Messages.CHERRY_BOMB_NAME;
	private int NumCycles;
	
	//Constructores
	
	public CherryBomb() {
		
	}
	public CherryBomb(int x, int y, GameWorld game) {
		super(cost, damage, endurance, plantName, x, y , game);
	}
	
	//Metodos

	@Override
	public String getName() {
		return this.plantName;
	}

	@Override
	public int getCost() {
		return this.cost;
	}

	@Override
	public int getDamage() {
		return this.damage;
	}
	
	@Override
	protected String getSymbol() {
		if(NumCycles <= 1) {
		return Messages.CHERRY_BOMB_SYMBOL;
		} else {
			return "C";
		}
	}

	@Override
	public String getDescription() {
		return Messages.PLANT_DESCRIPTION.formatted(Messages.CHERRY_BOMB_NAME_SHORTCUT, this.cost, this.damage, this.endurance);
	}
	
	

	@Override
	public Plant create(GameWorld game, int x, int y) {
		return new CherryBomb(x, y ,game);
	}

	@Override
	public void update() {
		if(NumCycles == 2) {
			game.pushAction(new ExplosionAction(col, row, damage, false));
			this.hp = 0;
		}
		NumCycles++;
		
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}
	
	

}
