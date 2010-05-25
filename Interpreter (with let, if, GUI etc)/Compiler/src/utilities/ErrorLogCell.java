package utilities;

import lebedev.Position;

public class ErrorLogCell {
	private String errorMessage;
	private Position position;

	public ErrorLogCell(String errorMessage, Position position) {
		this.errorMessage = errorMessage;
		this.position = position;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public Position getPosition() {
		return position;
	}

}
