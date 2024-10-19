package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.NotCatchablePositionException;
import tp1.p2.logic.Game;
import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class CatchCommand extends Command {

	private static boolean caughtSunThisCycle = false;

	private int col;

	private int row;

	public CatchCommand() {
		caughtSunThisCycle = false;
	}
	
	@Override
	protected void newCycleStarted() {
		caughtSunThisCycle = false;
	}

	private CatchCommand(int col, int row) {
		this.col = col;
		this.row = row;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_CATCH_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_CATCH_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_CATCH_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_CATCH_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		try {
			game.checkValidPlantPosition(col, row);  // Comprobamos que la posicion esta en el tablero
			if (!this.caughtSunThisCycle) {  // SI no hemos cogido un sol este ciclo
				game.tryToCatchObject(col, row);  // Intentamos cogerlo
				this.caughtSunThisCycle = true;
			}else {
				throw new CommandExecuteException (Messages.SUN_ALREADY_CAUGHT); // Un error en la ejecucion y lanzamos la excepcion
			}
		}catch (NotCatchablePositionException ncpe) {
			throw ncpe;  // Si no hay sol que coger, lanzamos la excepcion
		}
		return true;
	}

	@Override
	public Command create(String[] parameters) throws GameException {
		try {
			int col = Integer.parseInt(parameters[1]);
			int row = Integer.parseInt(parameters[2]);
			Command c = new CatchCommand(col, row);
			return c;
		}catch (NumberFormatException nfe) {  // Si alguna de las posiciones no es numero, lanzamos la excepcion
			throw new CommandParseException(Messages.INVALID_POSITION.formatted(parameters[1],parameters[2]),nfe);
		}
	}
}
