package tp1.p2.control.exceptions;

import java.io.IOException;

public class RecordException extends GameException{

	public RecordException(String message) {
		super(message);
	}

	public RecordException(Throwable cause) {
		super(cause);
	}

	public RecordException(String message, Throwable cause) {
		super(message, cause);
	}
}