package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sporty extends Zombie {
	
	//  ATRIBUTOS DE SPORTY  //
	
		private static int endurance = 2;
		private static int damage = 1;
		private static int speed = 1;
		private static String Name = Messages.SPORTY_ZOMBIE_NAME;
		private static int ZombieIdx = 2;
	
	//  CONSTRUCTORES DE SPORTY  //
	
		public Sporty() {}
		
		public Sporty(int x, int y, GameWorld game) {
			super(Name, endurance, damage, speed, ZombieIdx, x, y, game);	
		}
	
	//  METODOS DE SPORTY //

	@Override
	protected String getSymbol() {
		return Messages.SPORTY_ZOMBIE_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.ZOMBIE_DESCRIPTION.formatted(Messages.SPORTY_ZOMBIE_NAME,speed,damage,endurance);
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Zombie create(GameWorld game, int x, int y) {
		return new Sporty(x,y,game);
	}

	@Override
	protected int getIdx() {
		return this.ZombieIdx;
	}



	@Override
	public int getDamage() {
		return this.damage;
	}

}
