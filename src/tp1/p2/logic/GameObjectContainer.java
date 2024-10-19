package tp1.p2.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.view.Messages;

public class GameObjectContainer {

	private List<GameObject> gameObjects;

	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
	}

	public String positionToString(int col, int row) {
		StringBuilder buffer = new StringBuilder();
		boolean sunPainted = false;
		for(GameObject g : gameObjects) {
			if(g.isAlive() && g.getCol() == col && g.getRow() == row) {
				String objectText = g.toString();
				boolean sunAboutToPaint = objectText.contains(Messages.SUN_SYMBOL);
				 if (sunAboutToPaint && !sunPainted) {
			            buffer.append(objectText);
			            sunPainted = true;
			        } else if (!sunAboutToPaint) {
			            buffer.append(objectText);
			        }
			    }
			}

			return buffer.toString();
	}
	
	public void remove(GameItem g) {
		gameObjects.remove(g);
	}
	
	public boolean removeDead() {
		boolean ok = false;
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i).getHp() <= 0) {
				gameObjects.get(i).onExit();
				gameObjects.remove(i);
				ok = true;
			}
		}
		
		return ok;
	}
	
	boolean isPositionEmpty(int x, int y) {
		GameItem g = getGameObjectPos(x, y);
		if(g == null || !g.fillPosition()) {
			return true;
		}
		return false;
	}

	public GameItem getGameObjectPos(int x, int y) {
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i).getCol() == x && gameObjects.get(i).getRow() == y && gameObjects.get(i).fillPosition()) {
				return gameObjects.get(i);
			}
		}
		return null;
	}
	
	public void add(GameObject g) {
		this.gameObjects.add(g);
	}
	
	public void update() {
		for(int i = 0; i < this.gameObjects.size(); i++) {
			this.gameObjects.get(i).update();
		}
	}
	
	public GameItem getSun(int x, int y) {
		for(int i = 0; i < gameObjects.size(); i++) {
			GameObject g = gameObjects.get(i);
			if(g != null && !g.fillPosition()&& g.getCol() == x && g.getRow() == y) {
				return g;
			}
		}
		return null;
	}
	public boolean isPlayerDead() {
		for(int i = 0; i < this.gameObjects.size(); i++) {
			if(gameObjects.get(i).getCol() == -1) {
				return true; // interrumpe aqui
			}
		}
		return false;
	}

	public boolean isFullyOccupied(int col, int row){		
		GameItem g = getGameObjectPos(col,row);
		if (g == null) return false;
		else { 
			return true;
		}
	}

	public GameItem getNotFilledObject(int x, int y) {		// Devuelve los objetos que no ocupan casilla
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject g = gameObjects.get(i);
			if (g != null && !g.fillPosition() && g.getCol()== x && g.getRow()== y) {
				return g;
			}
		}
		return null;
	}


}
