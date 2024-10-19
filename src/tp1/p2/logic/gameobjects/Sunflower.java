package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sunflower extends Plant{
	
	private static String PlantName = Messages.SUNFLOWER_NAME;
	private static int damage = 0;	
	private static int cost = 20;	
	private static int endurance = 1;	
	private int Cycles;
	
	public Sunflower() {}
	
	public Sunflower(int x, int y, GameWorld game) {
		super(cost,damage,endurance,PlantName, x, y , game);
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
	public String getName() {
		return this.PlantName;
	}
	
	@Override
	protected String getSymbol() {
		return Messages.SUNFLOWER_SYMBOL;
	}
	
	@Override
	public String getDescription() {
		return Messages.PLANT_DESCRIPTION.formatted(Messages.SUNFLOWER_NAME_SHORTCUT, this.cost, this.damage, this.endurance);
	}
	
	@Override
	public void update() {
		generateSuncoins();
		
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
		return new Sunflower(x,y,game);
	}
	
	public boolean generateSuncoins() {
		
		if (this.Cycles == 3) {
			game.addSun();
			this.Cycles = 1;
			return true;
		}else {
			this.Cycles++;
			return false;
		}
	}
}
