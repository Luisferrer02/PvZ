package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class PlantFactory {

	/* @formatter:off */
	private static final List<Plant> AVAILABLE_PLANTS = Arrays.asList(new Sunflower(),new Peashooter(), new WallNut(), new CherryBomb());
	/* @formatter:on */

	public static boolean isValidPlant(String plantName) {
		for (Plant p : AVAILABLE_PLANTS) {
			if(p.matchPlant(plantName))
				return true;
		}

		return false;
	}

	public static Plant spawnPlant(String plantName, GameWorld game, int col, int row) throws GameException {
		if(isValidPlant(plantName)) {
			for(Plant p: AVAILABLE_PLANTS) {
				if(p.matchPlant(plantName)) {
					game.checkValidPlantPosition(col,row);
					return p.create(game, col, row);
				}
			}
		}
		else {
			throw new CommandExecuteException(Messages.INVALID_GAME_OBJECT);
		}
		return null;
	}

	public static List<Plant> getAvailablePlants() {
		return Collections.unmodifiableList(AVAILABLE_PLANTS);
	}

	/*
	 * Avoid creating instances of this class
	 */
	private PlantFactory() {
	}
}
