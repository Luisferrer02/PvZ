package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class WallNut extends Plant{
	
	//  ATRIBUTOS DE WALLNUT  //
	
	private static int cost = 50;
	private static int damage = 0;
	private static int endurance = 10;
	private static String PlantName = Messages.WALL_NUT_NAME;
	
	//  CONSTRUCTORES DE WALLNUT  //
	
	public WallNut() {}

	public WallNut(int x, int y, GameWorld game) {
		super( cost, damage, endurance,PlantName, x, y, game);
	}
	
	//  METODOS DE WALLNUT  //
	
		//  GETTERS  //
	
			@Override
			public int getCost() {
				return this.cost;
			}
			
			@Override
			public int getDamage() {
				return this.damage;
			}
			
			@Override
			public String getName() {
				return this.PlantName;
			}


	@Override
	protected String getSymbol() {
		return Messages.WALL_NUT_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.PLANT_DESCRIPTION.formatted(Messages.WALL_NUT_NAME_SHORTCUT, this.cost, this.damage, this.endurance);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
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
	public Plant create(GameWorld game, int x, int y) {
		return new WallNut(x,y,game);
	}

}
