package tp1.p2.control.commands;

import tp1.p2.control.Command;

import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.InvalidPositionException;

import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.logic.gameobjects.ZombieFactory;
import tp1.p2.view.Messages;

public class AddZombieCommand extends Command {

	private int zombieIdx;

	private int col;

	private int row;

	public AddZombieCommand() {

	}

	private AddZombieCommand(int zombieIdx, int col, int row) {
		this.zombieIdx = zombieIdx;
		this.col = col;
		this.row = row;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_ZOMBIE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_ZOMBIE_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_ZOMBIE_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_ZOMBIE_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
	    try {
	        Zombie z = ZombieFactory.spawnZombie(zombieIdx, game, col, row);  
	        game.isFullyOcuppied(col, row); 
	        game.addItem(z); //
	        game.update();
	    } catch (InvalidPositionException ipe) {
	        throw ipe;  
	    }

	    return true;
	}

	@Override
	public Command create(String[] parameters) throws GameException {
	    try {
	        int n = Integer.parseInt(parameters[1]);
	        int col = Integer.parseInt(parameters[2]);
	        int row = Integer.parseInt(parameters[3]);
	        return new AddZombieCommand(n, col, row);
	    } catch (NumberFormatException nfe) {
	        throw new CommandParseException(Messages.INVALID_POSITION.formatted(parameters[2], parameters[3]), nfe);
	    }
	}


}
