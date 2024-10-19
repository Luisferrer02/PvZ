package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class CommonZombie extends Zombie {
	
	//  ATRIBUTOS DE COMMONZOMBIE  //
	
		private static int endurance = 5;
		private static int damage = 1;
		private static int speed = 2;
		private static String Name = Messages.ZOMBIE_NAME;
		private static int ZombieIdx = 0;
	
	//  CONSTRUCTORES DE COMMONZOMBIE  //
		
		public CommonZombie() {}
		
		public CommonZombie(int x, int y, GameWorld game) {
			super(Name, endurance, damage, speed, ZombieIdx, x, y, game);	
		}
	
	//  METODOS DE COMMONZOMBIE //

	@Override
	protected String getSymbol() {
		return Messages.ZOMBIE_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.ZOMBIE_DESCRIPTION.formatted(Messages.ZOMBIE_NAME,speed,damage,endurance);
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Zombie create(GameWorld game, int x, int y) {
		return new CommonZombie(x,y,game);
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
