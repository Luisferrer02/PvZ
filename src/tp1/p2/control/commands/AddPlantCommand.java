package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.InvalidPositionException;
import tp1.p2.control.exceptions.NotEnoughCoinsException;
import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class AddPlantCommand extends Command implements Cloneable {

	private int col;

	private int row;

	private String plantName;

	private boolean consumeCoins;

	public AddPlantCommand() {
		this(true);
	}

	public AddPlantCommand(boolean consumeCoins) {
		this.consumeCoins = consumeCoins;
	}
	
	public AddPlantCommand(int x, int y, String s, boolean b) {
		this.col = x;
		this.row = y;
		this.plantName = s;
		this.consumeCoins = b;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
	    Plant p = PlantFactory.spawnPlant(plantName, game, col, row);  // Creamos la planta

	    try {
	        game.tryToBuy(p.getCost());  // Vemos si podemos comprarla
	        game.isFullyOcuppied(col, row);  // Vemos si está ocupada la posición
	        game.addItem(p);  // Añadimos la planta
	        game.consumeCoins(p.getCost());  // Consumimos las monedas
	        game.update();  // Actualizamos el juego
	    } catch (InvalidPositionException | NotEnoughCoinsException | CommandExecuteException ex) {
	        throw ex;  // Si hay una excepción, la relanzamos
	    }

	    return true;
	}

	@Override
	public Command create(String[] parameters) throws GameException {
	    if (parameters.length < 4) {
	        throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
	    }

	    try {
	        int col = Integer.parseInt(parameters[2]);
	        int row = Integer.parseInt(parameters[3]);
	        return new AddPlantCommand(col, row, parameters[1], true);  // Creamos el comando
	    } catch (NumberFormatException nfe) {
	        throw new CommandParseException(Messages.INVALID_POSITION.formatted(parameters[2], parameters[3]), nfe);
	    }
	}


}
