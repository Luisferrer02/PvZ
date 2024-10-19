package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.view.Messages;

public class ExplosiveZombie extends Zombie {
	
	//  ATRIBUTOS DE EXPLOSIVEZOMBIE  //
	
		private static int endurance = 5;
		private static int damage = 1;
		private static int speed = 2;
		private static String Name = Messages.EXPLOSIVE_ZOMBIE_NAME;
		private static int ZombieIdx = 3;
		
	//  CONSTRUCTORES DE EXPLOSIVEZOMBIE  //
		
		public ExplosiveZombie() {}
		
		public ExplosiveZombie(int x, int y, GameWorld game) {
			super(Name, endurance, damage, speed, ZombieIdx, x, y, game);	
		}
	
	//  METODOS DE EXPLOSIVEZOMBIE //

	@Override
	protected String getSymbol() {
		return Messages.EXPLOSIVE_ZOMBIE_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.ZOMBIE_DESCRIPTION.formatted(Messages.EXPLOSIVE_ZOMBIE_NAME, speed,damage,endurance);
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExit() {
		game.pushAction(new ExplosionAction(col,row,3, true));
		game.deadZombie();
		
	}
	
	@Override
	public Zombie create(GameWorld game, int x, int y) {
		return new ExplosiveZombie(x,y,game);
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
