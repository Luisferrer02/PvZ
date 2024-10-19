package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Peashooter extends Plant {
	private static int cost = 50;
	private static int damage = 1;
	private static int endurance = 3;
	private static String PlantName = Messages.PEASHOOTER_NAME;
	
	public Peashooter() {}

	public Peashooter(int x, int y, GameWorld game) {
		super( cost, damage, endurance,PlantName, x, y, game);
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
		return Messages.PEASHOOTER_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.PLANT_DESCRIPTION.formatted(Messages.PEASHOOTER_NAME_SHORTCUT, this.cost, this.damage, this.endurance);
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
		return new Peashooter(x,y,game);
	}
	

	@Override
	public void update() {
		int i = this.col + 1;
		while (i < game.NUM_COLS) { // Recorremos la fila del peashooter
			GameItem g = game.getGameItemInThisPos(i, this.row);
			if (g != null && g.fillPosition()) { // Si hay un objeto en esa posición y no es un sol
				boolean shoot = g.receivePlantAttack(this.damage);
				if (shoot) {
					break; // Si se realiza el ataque, salimos del bucle
				}
			}
			i++;
		}
	}

}
