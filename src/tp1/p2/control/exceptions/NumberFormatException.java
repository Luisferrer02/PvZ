package tp1.p2.control.exceptions;

public class NumberFormatException extends CommandParseException {

	public NumberFormatException(String message) {
		super(message);
	}

	public NumberFormatException(Throwable cause) {
		super(cause);
	}

	public NumberFormatException(String message, Throwable cause) {
		super(message, cause);
	}

}
