package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class BucketHead extends Zombie {
	
	//Atributos
	private static int endurance = 8;
	private static int damage = 1;
	private static int speed = 4;
	private static String Name = Messages.BUCKET_HEAD_ZOMBIE_NAME;
	private static int ZombieIdx = 1;
	//Constructores
	public BucketHead() {}
	public BucketHead(int x, int y, GameWorld game) {
		super(Name, endurance, damage, speed, ZombieIdx, x, y, game);
	}
	//Metodos

	@Override
	public Zombie create(GameWorld game, int x, int y) {
		return new BucketHead(x, y, game);
	}

	@Override
	protected int getIdx() {
		return this.ZombieIdx;
	}

	@Override
	protected String getSymbol() {
		return Messages.BUCKET_HEAD_ZOMBIE_SYMBOL;
		
	}

	@Override
	public String getDescription() {
		return Messages.ZOMBIE_DESCRIPTION.formatted(Messages.BUCKET_HEAD_ZOMBIE_NAME, speed,damage,endurance);
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDamage() {
		return this.damage;
	}

}
