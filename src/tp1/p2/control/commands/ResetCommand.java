package tp1.p2.control.commands;

//import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.Level;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ResetCommand extends Command {

	private Level level;

	private long seed;

	public ResetCommand() {
	}

	public ResetCommand(Level level, long seed) {
		this.level = level;
		this.seed = seed;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
	    if (level != null) {
	        game.reset(level, seed);
	    } else {
	        game.reset();
	    }
	    return true;
	}


	@Override
	public Command create(String[] parameters) throws GameException {
	    if (parameters.length > 1) {
	        try {
	            Level level;
	            long seed = 0;

	            if (parameters[1].equals("easy")) {
	                level = Level.EASY;
	            } else if (parameters[1].equals("hard")) {
	                level = Level.HARD;
	            } else if (parameters[1].equals("insane")) {
	                level = Level.INSANE;
	            } else {
	                return null;
	            }

	            if (parameters.length > 2) {
	                seed = Long.parseLong(parameters[2]);
	            }

	            return new ResetCommand(level, seed);
	        } catch (NumberFormatException nfe) {
	            throw new CommandParseException(Messages.INVALID_COMMAND, nfe);
	        }
	    }

	    return new ResetCommand();
	}

	

}
