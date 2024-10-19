package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.InvalidPositionException;
import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class AddPlantCheatCommand extends Command {
	
//  ATRIBUTOS DE ADDPLANTCHEATCOMMAND  //
	
	private int col;

	private int row;

	private String plantName;

	private boolean consumeCoins;
	
//  CONSTRUCTORES DE ADDPLANTCHEATCOMMAND  //
	
	public AddPlantCheatCommand() {
		this(true);
	}

	public AddPlantCheatCommand(boolean consumeCoins) {
		this.consumeCoins = consumeCoins;
	}
	
	public AddPlantCheatCommand(int x, int y, String s, boolean b) {
		this.col = x;
		this.row = y;
		this.plantName = s;
		this.consumeCoins = b;
	}
	
//  METODOS DE ADDPLANTCHEAT COMMAND // 

	@Override
	protected String getName() {
		return Messages.COMMAND_CHEAT_PLANT_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_CHEAT_PLANT_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_CHEAT_PLANT_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_CHEAT_PLANT_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
	    if (game.isFullyOcuppied(col, row)) {
	        throw new InvalidPositionException(Messages.INVALID_POSITION.formatted(col, row));
	    }
	    
	    Plant p = PlantFactory.spawnPlant(plantName, game, col, row);  // Creamos la planta
	    game.addItem(p);  // Añadimos la planta
	    game.update();    // Actualizamos el juego
	    return true;
	}

	@Override
	public Command create(String[] parameters) throws GameException {
	    try {
	        int col = Integer.parseInt(parameters[2]);
	        int row = Integer.parseInt(parameters[3]);
	        return new AddPlantCheatCommand(col, row, parameters[1], false);    // Creamos el comando de añadir planta sin pagar
	    } catch (NumberFormatException nfe) {
	        throw new CommandParseException(Messages.INVALID_POSITION.formatted(parameters[2], parameters[3]), nfe);
	    }
	}


}
