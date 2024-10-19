package tp1.p2.logic.actions;

import tp1.p2.logic.Game;
import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;

public class ExplosionAction implements GameAction {

	private int col;

	private int row;

	private int damage;
	
	private boolean zAttack;

	public ExplosionAction(int col, int row, int damage, boolean b) {
		this.col = col;
		this.row = row;
		this.damage = damage;
		this.zAttack = b;
	}

	@Override
	public void execute(GameWorld game) {
		for(int i = col -1; i <= col+1; i++) {
			for(int j = row-1; j <= row+1; j++) {
				GameItem g = game.getGameItemInThisPos(i, j);
				if(g!=null && g.fillPosition()) { //hay gameobject en esa posicion
					if(this.zAttack) {
						g.receiveZombieAttack(damage);
					}
					else {
						g.receivePlantAttack(damage);
						game.addPuntos(10);
					}
				}
			}
		}
	}

}
